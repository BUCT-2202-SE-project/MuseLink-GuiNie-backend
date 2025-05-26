package com.example.entity;

public class Log {
    private Integer logId;
    private String  operationType;
    private String  operationDetail;
    private String operationTime;
    private Integer operatorId;            // 对应表中的 operator_id

    // 无参构造 & 全参构造
    public Log() {}
    public Log(String operationType, String operationDetail, String operationTime, Integer operatorId) {
        this.operationType   = operationType;
        this.operationDetail = operationDetail;
        this.operationTime   = operationTime;
        this.operatorId      = operatorId;
    }

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getOperationDetail() {
        return operationDetail;
    }

    public void setOperationDetail(String operationDetail) {
        this.operationDetail = operationDetail;
    }

    public String getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(String operationTime) {
        this.operationTime = operationTime;
    }

    public Integer getOperationId() {
        return operatorId;
    }

    public void setOperationId(Integer operatorId) {
        this.operatorId = operatorId;
    }
}
