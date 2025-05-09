-- 创建数据库 manage
CREATE DATABASE IF NOT EXISTS manage
    DEFAULT CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

-- 使用数据库 manage
USE manage;
CREATE TABLE user (
                      user_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '用户ID(主键)',
                      username VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名',
                      password VARCHAR(255) NOT NULL COMMENT '密码（需加密存储）',
                      email VARCHAR(100) NOT NULL UNIQUE COMMENT '邮箱',
                      register_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
                      permission_status ENUM('正常', '受限') NOT NULL DEFAULT '正常' COMMENT '权限状态'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 管理员表（专用于管理员角色）
CREATE TABLE admin (
                       user_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '用户ID(主键)',
                       username VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名',
                       password VARCHAR(255) NOT NULL COMMENT '密码（需加密存储）',
                       email VARCHAR(100) NOT NULL UNIQUE COMMENT '邮箱',
                       register_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
                       role ENUM('管理员') NOT NULL DEFAULT '管理员' COMMENT '用户角色（固定）',
                       permission_status ENUM('正常', '受限') NOT NULL DEFAULT '正常' COMMENT '权限状态'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理员表';

-- 审核员表（专用于审核员角色）
CREATE TABLE reviewer (
                          user_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '用户ID(主键)',
                          username VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名',
                          password VARCHAR(255) NOT NULL COMMENT '密码（需加密存储）',
                          email VARCHAR(100) NOT NULL UNIQUE COMMENT '邮箱',
                          register_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
                          role ENUM('审核员') NOT NULL DEFAULT '审核员' COMMENT '用户角色（固定）',
                          permission_status ENUM('正常', '受限') NOT NULL DEFAULT '正常' COMMENT '权限状态'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='审核员表';

-- 文物表（存储文物基本信息）
CREATE TABLE artifact (
                          artifact_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '文物ID(主键)',
                          name VARCHAR(100) NOT NULL COMMENT '文物名称',
                          era VARCHAR(50) NOT NULL COMMENT '年代',
                          type VARCHAR(50) NOT NULL COMMENT '文物类型（如瓷器、书画等）',
                          description TEXT COMMENT '详细介绍',
                          image_url VARCHAR(255) COMMENT '图片存储路径',
                          likes INT DEFAULT 0 COMMENT '点赞数',
                          feature TEXT NOT NULL COMMENT '特征向量（字符串存储）'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文物表';

-- 点赞表（记录当前用户是否点赞当前显示文物）
CREATE TABLE likes (
                       like_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '点赞记录ID(主键)',
                       user_id INT NOT NULL COMMENT '用户ID',
                       artifact_id INT NOT NULL COMMENT '文物ID',
                       FOREIGN KEY (artifact_id) REFERENCES artifact(artifact_id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='点赞表';

-- 评论表（用户对文物的评论）
CREATE TABLE comment (
                         comment_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '评论ID(主键)',
                         content TEXT NOT NULL COMMENT '评论内容',
                         publish_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
                         user_id INT NOT NULL COMMENT '用户ID',
                         artifact_id INT NOT NULL COMMENT '文物ID',
                         review_status ENUM('通过', '未通过') NOT NULL DEFAULT '未通过' COMMENT '审核状态'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='评论表';

-- 收藏表（用户收藏文物记录）
CREATE TABLE collection (
                            collection_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '收藏ID(主键)',
                            user_id INT NOT NULL COMMENT '用户ID',
                            artifact_id INT NOT NULL COMMENT '文物ID',
                            collect_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '收藏时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='收藏表';

-- 新增问答表和主题表
-- 问答记录表
CREATE TABLE QA (
                    qa_id INT PRIMARY KEY AUTO_INCREMENT,
                    history_id INT NOT NULL,
                    content TEXT NOT NULL,
                    ask_time DATETIME NOT NULL,
                    user_id INT

);
-- 主题记录表
CREATE TABLE Topic (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       user_id INT NOT NULL,
                       history_id INT NOT NULL,
                       topic VARCHAR(255)
);

-- 审核记录表（审核员操作记录）
CREATE TABLE review (
                        review_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '审核ID(主键)',
                        content_type ENUM('评论', '动态', '媒体') NOT NULL COMMENT '审核内容类型',
                        comment_id INT COMMENT '关联评论ID（仅当类型为评论时有效）',
                        post_id INT COMMENT '关联动态ID（仅当类型为动态时有效）',
                        result ENUM('通过', '未通过') NOT NULL COMMENT '审核结果',
                        review_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '审核时间',
                        reviewer_id INT NOT NULL COMMENT '审核员ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='审核记录表';

-- 日志表（系统操作日志）
CREATE TABLE log (
                     log_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '日志ID(主键)',
                     operation_type ENUM('增', '删', '改', '备份', '恢复') NOT NULL COMMENT '操作类型',
                     operation_detail TEXT COMMENT '操作详情',
                     operation_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
                     operator_id INT NOT NULL COMMENT '操作用户ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统日志表';

-- 备份表（系统备份记录）
CREATE TABLE backup (
                        backup_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '备份ID(主键)',
                        backup_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '备份时间',
                        file_path VARCHAR(255) NOT NULL COMMENT '备份文件路径',
                        operator_id INT NOT NULL COMMENT '操作员ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='备份记录表';
