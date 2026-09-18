package service;

import model.Claim;
import model.FoundItem;
import model.LostItem;

import java.util.ArrayList;

public class ClaimService {

    private ArrayList<Claim> claims;
    private int nextClaimId;

    public ClaimService() {
        claims = new ArrayList<>();
        nextClaimId = 1;
    }

    public void createClaim(int lostItemId, int foundItemId, int claimantId) {

        String date = java.time.LocalDate.now().toString();

        Claim claim = new Claim(
                nextClaimId,
                lostItemId,
                foundItemId,
                claimantId,
                date
        );

        claims.add(claim);
        nextClaimId++;

        System.out.println("Claim submitted successfully!");
        System.out.println("Claim ID: " + claim.getClaimId());
    }

    public void viewClaims() {

        if (claims.isEmpty()) {
            System.out.println("No claims available.");
            return;
        }

        System.out.println("\n===== CLAIMS =====");

        for (Claim claim : claims) {
            System.out.println(claim);
            System.out.println("----------------------");
        }
    }

    public Claim findClaimById(int claimId) {

        for (Claim claim : claims) {
            if (claim.getClaimId() == claimId) {
                return claim;
            }
        }

        return null;
    }

    public void approveClaim(int claimId, ItemService itemService) {

        Claim claim = findClaimById(claimId);

        if (claim == null) {
            System.out.println("Claim not found.");
            return;
        }

        if (!claim.getStatus().equals("PENDING")) {
            System.out.println("This claim has already been processed.");
            return;
        }

        FoundItem foundItem =
                itemService.findFoundItemById(claim.getFoundItemId());

        LostItem lostItem =
                itemService.findLostItemById(claim.getLostItemId());

        if (foundItem == null || lostItem == null) {
            System.out.println("Associated item not found.");
            return;
        }

        claim.approveClaim();

        foundItem.markAsClaimed();
        lostItem.markAsFound();

        System.out.println("Claim approved successfully!");
        System.out.println("Item has been marked as returned.");
    }

    public void rejectClaim(int claimId) {

        Claim claim = findClaimById(claimId);

        if (claim == null) {
            System.out.println("Claim not found.");
            return;
        }

        if (!claim.getStatus().equals("PENDING")) {
            System.out.println("This claim has already been processed.");
            return;
        }

        claim.rejectClaim();

        System.out.println("Claim rejected.");
    }

    public ArrayList<Claim> getClaims() {
        return claims;
    }

    public void setClaims(ArrayList<Claim> claims) {

        this.claims = claims;
        nextClaimId = 1;

        for (Claim claim : claims) {

            if (claim.getClaimId() >= nextClaimId) {
                nextClaimId = claim.getClaimId() + 1;
            }
        }
    }
}