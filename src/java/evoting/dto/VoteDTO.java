
package evoting.dto;


public class VoteDTO {
    
     private String candidateId;
    private String voterId;

    public VoteDTO(String candidateId, String voter_id) {
        this.candidateId = candidateId;
        this.voterId = voter_id;
    }

    @Override
    public String toString() {
        return "VoteDTO{" + "candidateId=" + candidateId + ", voter_id=" + voterId + '}';
    }

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public String getVoterId() {
        return voterId;
    }

    public void setVoterId(String voterId) {
        this.voterId = voterId;
    }
    
   
}
