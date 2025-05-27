package com.example.entity;

public class Backup {
    private Integer backupId;
    private String backupTime; // 可改为 LocalDateTime
    private String filePath;
    private Integer operatorId;

    // Getters and Setters
    public Integer getBackupId() { return backupId; }
    public void setBackupId(Integer backupId) { this.backupId = backupId; }

    public String getBackupTime() { return backupTime; }
    public void setBackupTime(String backupTime) { this.backupTime = backupTime; }

    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }

    public Integer getOperatorId() { return operatorId; }
    public void setOperatorId(Integer operatorId) { this.operatorId = operatorId; }
}
