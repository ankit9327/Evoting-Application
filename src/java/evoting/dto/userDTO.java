
package evoting.dto;


public class userDTO {

    @Override
    public String toString() {
        return "userDTO{" + "userid=" + userid + ", password=" + password + '}';
    }

    public userDTO(String userid, String password) {
        this.userid = userid;
        this.password = password;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    private String userid;
    private String password;
}
