package com.secusociale.portail.service.dto.custom;

import com.secusociale.portail.domain.enumeration.ModeExecution;

public class JournalJobReportDTO {
    private ModeExecution modeExecution;
    private Long totalJobs;
    private Long completedJobs;
    private Long canceledJobs;
    private Long failedJobs;
    private Long totalSum;
    private Long validesSum;
    private Long erreursSum;

    public JournalJobReportDTO() {}

    public JournalJobReportDTO(ModeExecution modeExecution, Long totalJobs, Long completedJobs,
                               Long canceledJobs, Long failedJobs, Long totalSum, Long validesSum, Long erreursSum) {
        this.modeExecution = modeExecution;
        this.totalJobs = totalJobs;
        this.completedJobs = completedJobs;
        this.canceledJobs = canceledJobs;
        this.failedJobs = failedJobs;
        this.totalSum = totalSum;
        this.validesSum = validesSum;
        this.erreursSum = erreursSum;
    }

    public ModeExecution getModeExecution() { return modeExecution; }
    public void setModeExecution(ModeExecution modeExecution) { this.modeExecution = modeExecution; }

    public Long getTotalJobs() { return totalJobs; }
    public void setTotalJobs(Long totalJobs) { this.totalJobs = totalJobs; }

    public Long getCompletedJobs() { return completedJobs; }
    public void setCompletedJobs(Long completedJobs) { this.completedJobs = completedJobs; }

    public Long getCanceledJobs() { return canceledJobs; }
    public void setCanceledJobs(Long canceledJobs) { this.canceledJobs = canceledJobs; }

    public Long getFailedJobs() { return failedJobs; }
    public void setFailedJobs(Long failedJobs) { this.failedJobs = failedJobs; }

    public Long getTotalSum() { return totalSum; }
    public void setTotalSum(Long totalSum) { this.totalSum = totalSum; }

    public Long getValidesSum() { return validesSum; }
    public void setValidesSum(Long validesSum) { this.validesSum = validesSum; }

    public Long getErreursSum() { return erreursSum; }
    public void setErreursSum(Long erreursSum) { this.erreursSum = erreursSum; }
}
