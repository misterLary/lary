package com.cjb.model;

import java.util.Date;

public class LogInfo {

    private Integer id;

    private String operator;

    private String operateType;

    private Date operateDate;

    private String operateResult;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public Date getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(Date operateDate) {
        this.operateDate = operateDate;
    }

    public String getOperateResult() {
        return operateResult;
    }

    public void setOperateResult(String operateResult) {
        this.operateResult = operateResult;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "LogInfo{" +
                "id=" + id +
                ", operateor='" + operator + '\'' +
                ", operateType='" + operateType + '\'' +
                ", operateDate=" + operateDate +
                ", operateResult='" + operateResult + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
