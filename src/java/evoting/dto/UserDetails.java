
package evoting.dto;


public class UserDetails {

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public String getUserid() {
        return userid;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getPassword() {
        return password;
    }

    public long getMobile() {
        return mobile;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "UserDetails{" + "username=" + username + ", userid=" + userid + ", email=" + email + ", address=" + address + ", city=" + city + ", password=" + password + ", mobile=" + mobile + ", gender=" + gender + '}';
    }

    public UserDetails(String username, String userid, String email, String address, String city, long mobile) {
        this.username = username;
        this.userid = userid;
        this.email = email;
        this.address = address;
        this.city = city;
        this.mobile = mobile;
    }

    public UserDetails(String username, String userid, String email, String address, String city, String password, long mobile, String gender) {
        this.username = username;
        this.userid = userid;
        this.email = email;
        this.address = address;
        this.city = city;
        this.password = password;
        this.mobile = mobile;
        this.gender = gender;
    }

    public UserDetails() {
    }
     private String username;
    private String userid;
    private String email;
    private String address;
    private String city;
    private String password;
    private long mobile;
    private String gender;

  }
