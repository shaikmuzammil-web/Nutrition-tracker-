package P1;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

class UnderAgeException extends Exception
{
	public UnderAgeException(String s)
	{
		super(s);
	}
}
class ObesityException extends Exception
{
	public ObesityException(String s)
	{
		super(s);
	}
}
class AadharException extends Exception
{
	public AadharException(String s)
	{
		super(s);
	}
}

class USER
{
	private String Name,Addar_number,gender;
	private int Age;
	private double Body_weight,Height;
	private boolean Vegeterian;
	public USER(Scanner sc)
	throws UnderAgeException,InputMismatchException,AadharException
	{
		System.out.println("Enter Name : ");
		this.Name=sc.next();
		System.out.println("Enter Aadhar Number\n(EX:1937-6453-8724):");
		this.Addar_number = sc.next();
		if (Addar_number.length() != 14)  
		  throw new AadharException("Aadhar must be 14 characters");
		if (Addar_number.charAt(4)!='-'||this.Addar_number.charAt(9)!='-')  
		  throw new AadharException("Invalid Aadhar format Must contain '-' " );
		for (int i = 0; i < Addar_number.length(); i++) 
		{
		    if (i == 4||i == 9) 
		      continue;
		    char ch = Addar_number.charAt(i);
		    if (ch < '0'|| ch > '9') 
		      throw new AadharException("Aadhar should contain only digits");
		}

		System.out.println("Enter Age : ");
		this.Age=sc.nextInt();
		if(this.Age<18)
		{
			throw new UnderAgeException("you are under age. You are not suggested to this activities");
		}
			System.out.println("Enter Height(in meter) : ");
		this.Height=sc.nextDouble();
		System.out.println("Enter Gender : ");
		this.gender=sc.next();
		System.out.println("Enter Weight : ");
		this.Body_weight=sc.nextDouble();
		System.out.println("Enter true for vegetarian or false : ");
		this.Vegeterian=sc.nextBoolean();
	}
	public double getWeight()
	{
		return this.Body_weight;
	}
	public double getHeight()
	{
		return this.Height;
	}
	public boolean getveg()
	{
		return this.Vegeterian;
	}
	public void display()
	{
		System.out.println("Name is "+this.Name);
		System.out.println("Addar number is "+this.Addar_number);
		System.out.println("Age is"+this.Age);
		System.out.println("Gender is"+this.gender);
		System.out.println("Body Weight is "+this.Body_weight);
		System.out.println("Body Height is "+this.Height);
		System.out.println("User is Vegeterian"+this.Vegeterian);
	}
	public void UserDetails(FileWriter FW,String S1)
	throws IOException
	{
		
		FW=new FileWriter(S1,true);
		FW.append("\n");
		FW.append("User Name is ");
		FW.append(this.Name+"\n");
		FW.append("User ID is ");
		FW.append(this.Addar_number+"\n");
		FW.append("User Age is ");
		FW.append(this.Age+"\n");
		FW.append("User Gender is ");
		FW.append(this.gender+"\n");
		FW.append("User Body Weight is ");
		FW.append(this.Body_weight+"\n");
		FW.append("User height is ");
		FW.append(this.Height+"\n");
		FW.append("User is Vegiterian");
		FW.append(this.Vegeterian+"\n");
		FW.close();
		
		
	}
	
}
class FOOD_INTAKE extends USER
{
	double Cbr,Clu,Cdi,total_cal;
	public FOOD_INTAKE(Scanner sc,Files F,FileReader FR,String S1)
	throws InputMismatchException,UnderAgeException,IOException,AadharException
	{
		super(sc);
		System.out.println("By seeing the following chart enter the folling:");
		F.reader(FR, S1);
		System.out.println("Enter calories intake in Breakfast");
		this.Cbr=sc.nextDouble();
		System.out.println("Enter calories intake in lunch");
		this.Clu=sc.nextDouble();
		System.out.println("Enter calories intake in Dinner");
		this.Cdi=sc.nextDouble();
		this.total_cal=this.Cbr+this.Clu+this.Cdi;
	}
	public void UserDetails(FileWriter FW,String S1)
	throws IOException
	{
		super.UserDetails(FW,S1);
		FW=new FileWriter(S1,true);
		FW.append("calory intake in Breakfast ");
		FW.append(this.Cbr+"\n");
		FW.append("Calory intake in Lunch");
		FW.append(this.Clu+"\n");
		FW.append("Calory intake in Dinner");
		FW.append(this.Cdi+"\n");
		FW.append("Total calory intake is ");
		FW.append(this.total_cal+"\n");
		FW.close();
	}		
	
}
class NUTRITION
{
	private double BMI; 
	private String condition;
	public void condition_check(double W,double H)
	{
		this.BMI=W/(H*H);
	}
	public void BMII()
	throws ObesityException
	{
		if(this.BMI<=18.5)
		{
			this.condition="UnderWeight";
		}
		else if(this.BMI<=24.9)
		{
			this.condition="Normal";
			System.out.println("maintain the same routine daily");
		}
		else if(this.BMI<=29.9)
		{
			this.condition="OverWeight";
		}
		else 
		{
			this.condition="Obesity";
		}
	}
	public String getcondition()
	{
		return this.condition;
	}
	public void UserDetails(FileWriter FW,String S1)
	throws IOException
	{
		FW=new FileWriter(S1,true);
		FW.append("The BMI value =");
		FW.append(this.BMI+"\n");
		FW.append("Your are ");
		FW.append(this.condition);
		FW.append(" patient\n");
		FW.close();
	}		
}
class Files
{
	public void reader(FileReader FR,String S1)
	throws IOException
	{
		FR=new FileReader(S1);
		int ch;
		while ((ch=FR.read())!=-1)
		{
			System.out.print((char)ch);
		}
		System.out.println("\n");
		FR.close();
	}
	public void reader_Writer(FileReader FR,FileWriter FW,String S1,String S2)
	throws IOException
	{
		FR=new FileReader(S1);
		FW=new FileWriter(S2,true);

		int ch;
		while ((ch=FR.read())!=-1)
		{
			FW.append((char)ch);
		}
		FR.close();
		FW.close();
	}
}

public class project {
	public static void main (String [] args)
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter no.of Users: ");
	    int size=sc.nextInt();
	    FileReader FR=null;
		FileWriter FW=null;
	    USER [] U =new USER[size];
	    NUTRITION [] N=new NUTRITION[size];
	    Files F=new Files();
		try
		{
		
	  
	    String choice="no";
	   do {
	    choice="no";
		System.out.println("Menu");
		System.out.println("-----------------------------------------------------------------");
		System.out.println("1...User Profile");
		System.out.println("2...All user details");
		System.out.println("3...Get deit Plan");
		System.out.println("4...Training Schedule");
		System.out.println("5...All user report");
		System.out.println("6...Exit");
		System.out.println("enter the choice:");
		int ch=sc.nextInt();
		
		
		
		switch(ch)
		{
		case 1:
		
			for(int i=0;i<size;i++)
		    {
		    	System.out.println("Enter User "+(i+1)+" Details: ");
		    	U[i]=new FOOD_INTAKE(sc,F,FR,"C:\\Sample\\CALARIES_CHART.txt");
		    	N[i]=new NUTRITION();
		    	N[i].condition_check(U[i].getWeight(),U[i].getHeight());
		    	N[i].BMII();
		    }
		    break;
		case 2:
			System.out.println("Enter the file location to enter the details of the user:");
			String S2=sc.next();
			for(int a=0;a<size;a++)
			{
				U[a].UserDetails(FW,S2);
				N[a].UserDetails(FW, S2);
			}
			break;
		case 3:
			
			System.out.println("Enter Condition of your");
			String conD=sc.next();
			System.out.println("Enter the file location ");
			String fnameD=sc.next();
			if(conD.equalsIgnoreCase("OverWeight"))
			{
				F.reader_Writer(FR, FW,"C:\\Sample\\OverWeight.txt",fnameD);
			}
			else if(conD.equalsIgnoreCase("Normal"))
			{
				F.reader_Writer(FR, FW,"C:\\Sample\\Normal.txt",fnameD);
			}
			else if(conD.equalsIgnoreCase("UnderWeight"))
			{
				F.reader_Writer(FR, FW,"C:\\Sample\\UnderWeight.txt",fnameD);
			}
			break;
		case 4:
			
			System.out.println("Enter Condition of your");
			String conE=sc.next();
			System.out.println("Enter the file location ");
			String fnameE=sc.next();
			if(conE.equalsIgnoreCase("UnderWeight"))
			{
				F.reader_Writer(FR, FW,"C:\\Sample\\OverWeightE.txt",fnameE);
			}
			else if(conE.equalsIgnoreCase("Normal"))
			{
				F.reader_Writer(FR, FW,"C:\\Sample\\NormalE.txt",fnameE);
			}
			else if(conE.equalsIgnoreCase("OverWeight"))
			{
				F.reader_Writer(FR, FW,"C:\\Sample\\UnderWeightE.txt",fnameE);
			}
			break;
		case 5:
		
			System.out.println("Enter the file location to enter the report of the user:");
			String fnameR=sc.next();
			for(int a=0;a<size;a++)
			{
				U[a].UserDetails(FW,fnameR);
				N[a].UserDetails(FW, fnameR);
				if(N[a].getcondition().equalsIgnoreCase("UnderWeight"))
				{
					F.reader_Writer(FR, FW,"C:\\Sample\\OverWeight.txt",fnameR);
					F.reader_Writer(FR, FW,"C:\\Sample\\OverWeightE.txt",fnameR);
				}
				else if(N[a].getcondition().equalsIgnoreCase("Normal"))
				{
					F.reader_Writer(FR, FW,"C:\\Sample\\Normal.txt",fnameR);
					F.reader_Writer(FR, FW,"C:\\Sample\\NormalE.txt",fnameR);
				}
				else if(N[a].getcondition().equalsIgnoreCase("OverWeight"))
				{
					F.reader_Writer(FR, FW,"C:\\Sample\\UnderWeight.txt",fnameR);
					F.reader_Writer(FR, FW,"C:\\Sample\\UnderWeightE.txt",fnameR);
				}
				
			}
			break;
		case 6:
		     System.out.println("Thank You !!!!");	
		     break;	
		}
		if(ch!=6)
		{
		System.out.println("\nEnter yes to repeat again: ");
		choice=sc.next();
		}
		}while(choice.equalsIgnoreCase("yes"));
	}
		catch(InputMismatchException e)
		{
			System.out.println("Erro "+e.getMessage());
		}
		catch(NullPointerException e)
		{
			System.out.println("Error "+e.getMessage());
		}
		catch(AadharException e)
		{
			System.out.println("Error "+e.getMessage());
		}
		
		catch(ObesityException e)
		{
			System.out.println(e.getMessage());
		}
		catch(UnderAgeException e)
		{
			System.out.println("error "+e.getMessage());
		}
		catch(IOException e)
		{
			System.out.println("error "+e.getMessage());
		}
		
		
	}
}

