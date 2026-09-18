package model;

import java.io.Serializable;

public class Claim implements Serializable {

    private int claimId;
    private int lostItemId;
    private int foundItemId;
    private int claimantId;
    private String status;
    private String claimDate;

    public Claim(int claimId, int lostItemId, int foundItemId,
                 int claimantId, String claimDate) {

        this.claimId = claimId;
        this.lostItemId = lostItemId;
        this.foundItemId = foundItemId;
        this.claimantId = claimantId;
        this.claimDate = claimDate;
        this.status = "PENDING";
    }

    public int getClaimId() {
        return claimId;
    }

    public int getLostItemId() {
        return lostItemId;
    }

    public int getFoundItemId() {
        return foundItemId;
    }

    public int getClaimantId() {
        return claimantId;
    }

    public String getStatus() {
        return status;
    }

    public String getClaimDate() {
        return claimDate;
    }

    public void approveClaim() {
        status = "APPROVED";
    }

    public void rejectClaim() {
        status = "REJECTED";
    }

    @Override
    public String toString() {
        return "----- CLAIM -----\n" +
                "Claim ID: " + claimId +
                "\nLost Item ID: " + lostItemId +
                "\nFound Item ID: " + foundItemId +
                "\nClaimant ID: " + claimantId +
                "\nStatus: " + status +
                "\nClaim Date: " + claimDate;
    }
}