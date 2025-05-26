#!/bin/bash

# 数据库信息（变量定义方式改变，去掉set和引号）
DB_NAME=manage

# 备份目录和日期（使用Linux的date命令）
BACKUP_DIR=/backup
DATE=$(date +"%Y%m%d")  # 格式化为年月日

# 备份文件名（路径分隔符改为正斜杠）
FULL_BACKUP_FILE="${BACKUP_DIR}/${DB_NAME}-full-${DATE}.sql"

# MySQL配置（注意Linux下通常是my.cnf而不是my.ini）
MY_CNF=D:/IDEA_project/zuoye/farm_system/springboot/src/main/resources/BackUp_shell/my.ini  # 需要改为你的实际配置文件路径
MYSQL_PATH=D:/MYSQL/MySQL Server 8.0     # Linux下MySQL通常安装在/usr/bin

# 创建备份目录（-p参数自动创建多级目录）
mkdir -p "$BACKUP_DIR"

# 执行全量备份（命令参数基本相同，但变量引用方式变化）
${MYSQL_PATH}/mysqldump \
    --defaults-extra-file="$MY_CNF" \
    --single-transaction \
    --source-data=2 \
    --flush-logs \
    "$DB_NAME" > "$FULL_BACKUP_FILE"

# 检查结果（Linux使用$?而不是errorlevel）
if [ $? -ne 0 ]; then
    echo "全量备份失败！"
    exit 1
fi

# 记录binlog位置（命令不变，但输出重定向语法相同）
MASTER_STATUS_FILE="${BACKUP_DIR}/last_full_backup_master_status.txt"
${MYSQL_PATH}/mysql \
    --defaults-extra-file="$MY_CNF" \
    --skip-column-names \
    -e "SHOW MASTER STATUS;" > "$MASTER_STATUS_FILE"

# 检查状态是否成功
if [ $? -ne 0 ]; then
    echo "记录binlog位置失败！请检查 ${BACKUP_DIR}/master_status_error.log"
    exit 1
fi

echo "备份成功完成！"
echo "全量备份文件：${FULL_BACKUP_FILE}"
echo "主库状态文件：${MASTER_STATUS_FILE}"