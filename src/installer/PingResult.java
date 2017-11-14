/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package installer;

public class PingResult {

  private String ipAddress;
  private boolean resultCode;

  public PingResult(String ipAddress, boolean resultCode) {
    this.ipAddress = ipAddress;
    this.resultCode = resultCode;
  }
  
  public String getIpAddress() {
    return ipAddress;
  }

  public boolean getResultCode() {
    return resultCode;
  }

  public String toString() {
    return "IpAddress :: "+ ipAddress + " Result Code : "+ resultCode;
  }
}