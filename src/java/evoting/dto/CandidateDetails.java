
package evoting.dto;

import java.util.Objects;


public class CandidateDetails {

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.candidateId);
        hash = 53 * hash + Objects.hashCode(this.userId);
        hash = 53 * hash + Objects.hashCode(this.candidateName);
        hash = 53 * hash + Objects.hashCode(this.symbol);
        hash = 53 * hash + Objects.hashCode(this.party);
        hash = 53 * hash + Objects.hashCode(this.city);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CandidateDetails other = (CandidateDetails) obj;
        if (!Objects.equals(this.candidateId, other.candidateId)) {
            return false;
        }
        if (!Objects.equals(this.userId, other.userId)) {
            return false;
        }
        if (!Objects.equals(this.candidateName, other.candidateName)) {
            return false;
        }
        if (!Objects.equals(this.symbol, other.symbol)) {
            return false;
        }
        if (!Objects.equals(this.party, other.party)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CandidateDetails{" + "candidateId=" + candidateId + ", userId=" + userId + ", candidateName=" + candidateName + ", symbol=" + symbol + ", party=" + party + ", city=" + city + '}';
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCandidateId() {
        return candidateId;
    }

    public String getUserId() {
        return userId;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getParty() {
        return party;
    }

    public String getCity() {
        return city;
    }
    
    private String candidateId;
    private String userId;
    private String candidateName;
    private String symbol;
    private String party;
    private String city;
    
}
