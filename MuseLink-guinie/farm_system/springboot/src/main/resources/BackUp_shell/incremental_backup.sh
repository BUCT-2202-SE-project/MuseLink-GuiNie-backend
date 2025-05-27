#!/bin/bash

# 数据库信息
DB_NAME=manage

# 备份目录设置（使用Linux路径格式）
BACKUP_DIR=/backup
DIFF_BACKUP_DIR=/diff_backup

# 生成日期时间戳（Linux日期格式）
DATE=$(date +"%Y%m%d")
DIFF_BACKUP_FILE="${DIFF_BACKUP_DIR}/${DB_NAME}-diff-${DATE}.sql"

# 二进制日志目录（典型MySQL Linux安装路径）
BINLOG_FILE_DIR=/var/lib/mysql

# MySQL配置（Linux通常使用my.cnf）
MY_CNF=/etc/mysql/my.cnf
MYSQL_PATH=/usr/bin

# 检查差异备份目录
if [ ! -d "$DIFF_BACKUP_DIR" ]; then
    mkdir -p "$DIFF_BACKUP_DIR"
    if [ $? -ne 0 ]; then
        echo "Error: Failed to create directory $DIFF_BACKUP_DIR"
        exit 1
    else
        echo "Successfully created directory: $DIFF_BACKUP_DIR"
    fi
else
    echo "Directory already exists: $DIFF_BACKUP_DIR"
fi

# 调试输出变量
echo "[DEBUG] DIFF_BACKUP_FILE=$DIFF_BACKUP_FILE"
echo "[DEBUG] BINLOG_FILE_DIR=$BINLOG_FILE_DIR"

# 读取全量备份的binlog信息
MASTER_STATUS_FILE="$BACKUP_DIR/last_full_backup_master_status.txt"
if [ ! -f "$MASTER_STATUS_FILE" ]; then
    echo "Error: Run full backup first"
    exit 1
fi

# 读取binlog文件和位置（Linux文本处理方式）
BINLOG_FILE=$(awk '{print $1}' "$MASTER_STATUS_FILE")
BINLOG_POS=$(awk '{print $2}' "$MASTER_STATUS_FILE")

if [ -z "$BINLOG_FILE" ]; then
    echo "Error: Binlog file not found"
    exit 1
fi
if [ -z "$BINLOG_POS" ]; then
    echo "Error: Binlog position not found"
    exit 1
fi

# 调试输出binlog信息
echo "[DEBUG] BINLOG_FILE=$BINLOG_FILE, BINLOG_POS=$BINLOG_POS"

# 修正起始位置为4（若为0）
if [ "$BINLOG_POS" = "0" ]; then
    BINLOG_POS=4
fi

# 列出binlog文件（Linux命令）
echo "[DEBUG] Listing binlog files in $BINLOG_FILE_DIR/"
ls -1 "$BINLOG_FILE_DIR/${DB_NAME}-bin."* | sort

# 生成差异备份（按文件名排序处理）
echo "[DEBUG] Generating differential backup..."
FIRST_FILE=1

for CURRENT_FILE in $(ls -1 "$BINLOG_FILE_DIR/${DB_NAME}-bin."* | sort); do
    filename=$(basename "$CURRENT_FILE")
    echo "[DEBUG] Found file: $filename"

    if [ "$filename" = "${DB_NAME}-bin.index" ]; then
        echo "[DEBUG] Skipping file: ${DB_NAME}-bin.index"
        continue
    fi

    # Linux字符串比较（按字典序）
    if [[ "$filename" > "$BINLOG_FILE" ]] || [[ "$filename" == "$BINLOG_FILE" ]]; then
        echo "[DEBUG] Processing file: $filename"
        if [ $FIRST_FILE -eq 1 ]; then
            "$MYSQL_PATH/mysqlbinlog" --defaults-extra-file="$MY_CNF" --force-if-open --start-position="$BINLOG_POS" "$CURRENT_FILE" > "$DIFF_BACKUP_FILE"
            FIRST_FILE=0
        else
            "$MYSQL_PATH/mysqlbinlog" --defaults-extra-file="$MY_CNF" --force-if-open --start-position=4 "$CURRENT_FILE" >> "$DIFF_BACKUP_FILE"
        fi

        if [ $? -ne 0 ]; then
            echo "ERROR: Failed to process $filename" >> "$DIFF_BACKUP_DIR/error.log"
            exit 1
        fi
    else
        echo "[DEBUG] Skipping file: $filename (lt $BINLOG_FILE)"
    fi
done

# 检查是否处理了文件
if [ $FIRST_FILE -eq 1 ]; then
    echo "Error: No binlog files processed. Check if files exist after $BINLOG_FILE."
    exit 1
fi

# 检查文件是否生成且非空（Linux文件检查方式）
if [ -f "$DIFF_BACKUP_FILE" ]; then
    if [ -s "$DIFF_BACKUP_FILE" ]; then
        echo "Differential backup created: $DIFF_BACKUP_FILE"
    else
        echo "Error: Backup file is empty"
        rm -f "$DIFF_BACKUP_FILE"
        exit 1
    fi
else
    echo "Error: No backup file generated"
    exit 1
fi