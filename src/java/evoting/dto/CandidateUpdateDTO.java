
package evoting.dto;

import java.io.InputStream;


public class CandidateUpdateDTO {

    @Override
    public String toString() {
        return "CandidateUpdateDTO{" + "candidateId=" + candidateId + ", city=" + city + ", party=" + party + ", symbol=" + symbol + '}';
    }

    public CandidateUpdateDTO(String candidateId, String city, String party, InputStream symbol) {
        this.candidateId = candidateId;
        this.city = city;
        this.party = party;
        this.symbol = symbol;
    }

    public CandidateUpdateDTO() {
    }

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public InputStream getSymbol() {
        return symbol;
    }

    public void setSymbol(InputStream symbol) {
        this.symbol = symbol;
    }

  private String candidateId;
    private String city;
    private String party;
     private InputStream symbol;   
}
