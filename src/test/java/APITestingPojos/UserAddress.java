package APITestingPojos;

public class UserAddress 
{
	
	private String plotNumber;
	private String street;
    private String state;
    private String country;
    private String zipCode;
	
    public UserAddress()
    {
    	
    }
    
    public UserAddress(String pnumber,String str,String st,String c,String zip)
    {
    	setPlotNumber(pnumber);
    	setStreet(str);
    	setState(st);
    	setCountry(c);
    	setZipCode(zip);
    	
    }

	public String getPlotNumber()
    {
		return plotNumber;
	}
	public void setPlotNumber(String pnumber) 
	{
		this.plotNumber = pnumber;
	}
	public String getStreet() 
	{
		return street;
	}
	public void setStreet(String street) 
	{
		this.street = street;
	}
	public String getState()
	{
		return state;
	}
	public void setState(String state)
	{
		this.state = state;
	}
	public String getCountry() 
	{
		return country;
	}
	public void setCountry(String country)
	{
		this.country = country;
	}
	public String getZipCode() 
	{
		return zipCode;
	}
	public void setZipCode(String zipCode) 
	{
		this.zipCode = zipCode;
	}
	
}
