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

class User
{
	private String Name,Addar_number,gender;
	private int Age;
	private double Body_weight,Height;
	private boolean Vegeterian;
	public User(Scanner sc)
	throws UnderAgeException,InputMismatchException
	{
		System.out.println("Enter Name : ");
		this.Name=sc.next();
		System.out.println("Enter Aadhar Card Number : ");
		sc.nextLine();
		this.Addar_number=sc.nextLine();
		System.out.println("Enter Age : ");
		this.Age=sc.nextInt();
		if(this.Age<18)
			throw new UnderAgeException("you are under age. You are not suggested to this activities");
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
	public void report(FileWriter FW)
	throws IOException
	{
		
		FW=new FileWriter("C:\\Sample\\REPORT1.txt",true);
		FW.append("\n");
		FW.append(this.Name+"\n");
		FW.append(this.Addar_number+"\n");
		FW.append(this.Age+"\n");
		FW.append(this.gender+"\n");
		FW.append(this.Body_weight+"\n");
		FW.append(this.Height+"\nTHe User is Vegetarian ");
		FW.append(this.Vegeterian+"\n");
		FW.close();
		
		
	}
	
}
class Food_Intake extends User
{
	double Cbr,Clu,Cdi,total_cal;
	public Food_Intake(Scanner sc)
	throws InputMismatchException,UnderAgeException
	{
		super(sc);
		System.out.println("By seeing the following chart enter the folling:");
		
		System.out.println("Enter calories intake in Breakfast");
		this.Cbr=sc.nextDouble();
		System.out.println("Enter calories intake in lunch");
		this.Clu=sc.nextDouble();
		System.out.println("Enter calories intake in Dinner");
		this.Cdi=sc.nextDouble();
		this.total_cal=this.Cbr+this.Clu+this.Cdi;
	}
	public void report(FileWriter FW)
	throws IOException
	{
		super.report(FW);
		
		FW=new FileWriter("C:\\Sample\\REPORT1.txt",true);
		FW.append(this.Cbr+"\n");
		FW.append(this.Clu+"\n");
		FW.append(this.Cdi+"\n");
		FW.append(this.total_cal+"\n");
		FW.close();
		
		
	}
}
class nutrition
{
	private double BMI; 
	private String condition;
	public void calculation(User U)
	{
		this.BMI=U.getWeight()/(U.getHeight()*U.getHeight());
	}
	public void BMII()
	{
		if(this.BMI<=18.5)
		{
			this.condition="Under Weight";
		}
		else if(this.BMI<=24.9)
		{
			this.condition="Healthy";
			System.out.println("maintain the same routine daily");
		}
		else if(this.BMI<=29.9)
		{
			this.condition="Over weight";
		}
		else 
		{
			this.condition="Obesity";
		}
		
	}
	public void report(FileWriter FW)
	throws IOException
	{
		
		FW=new FileWriter("C:\\Sample\\REPORT1.txt",true);
		FW.append(this.BMI+"\n");
		FW.append(this.condition+"\n");
		FW.close();
	
		
	}
	public String getcon()
	{
		return this.condition;
	}
	
	public void Vege(FileReader FR)
	throws IOException
	{
		FR=new FileReader("C:\\Sample\\UnderWeightE.txt");
		int ch=0;
		while(ch!=-1)
		{
			ch=FR.read();
			System.out.print((char)ch);
		}
		FR.close();
	}
	public void nonveg(FileReader FR)
	throws IOException
	{
		FR=new FileReader("C:\\Sample\\UnderWeight.txt");
		int ch=0;
		while(ch!=-1)
		{
			ch=FR.read();
			System.out.print((char)ch);
		}
		FR.close();
	 }
		
	public void Exercise(FileReader FR)
	throws IOException
	{
		System.out.println("Do the respective excersice according to the chart");
		FR=new FileReader("C:\\Sample\\OverWeight.txt");
		int ch=0;
		while(ch!=-1)
		{
			ch=FR.read();
			System.out.print((char)ch);
		}
		FR.close();
		
	}
		
}


public class Example1 {
	public static void main (String [] args)
	{
		try
		{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Number of Users: ");
	    int size=sc.nextInt();
	    FileReader FR=null;
		FileWriter FW=null;
	    User [] U =new Food_Intake[size];
	    nutrition [] N=new nutrition[size];
	    N[0]=new nutrition();
	    String choice;
	   do {
	    
		System.out.println("Menu");
		System.out.println("==========================================================");
		System.out.println("1...User Profile");
		System.out.println("2...Daily Food-Intake");
		System.out.println("3...All user details");
		System.out.println("4...All User Report");
		System.out.println("5...Training Schedule");
		System.out.println("6...Exit");
		System.out.println("enter the choice:");
		int ch=sc.nextInt();
		
		
		
		switch(ch)
		{
		case 1:
			for(int i=0;i<size;i++)
		    {
		    	System.out.println("Enter The "+(i+1)+" User Details: ");
		    	U[i]=new Food_Intake(sc);
		    }
		    break;
		case 2:
		
			for(int a=0;a<size;a++)
			{
				if(U[a].getveg())
			        N[a].Vege(FR);
				else
					N[a].nonveg(FR);

			N[a].BMII();
			N[a].calculation(U[a]);
			}
			break;
		case 3:
			System.out.println("All user details are");
			for(int a=0;a<size;a++)
			{
				U[a].display();
			}
			break;
		case 4:
			
			for(int a=0;a<size;a++)
			{
				U[a].report(FW);
				N[a].report(FW);
				if(N[a].getcon().equalsIgnoreCase("Over weight"))
				{
					N[a].Exercise(FR);
				}
				
				System.out.println("File clossed successfully");
				
				
			}
			break;
		case 5:
			System.out.println("Training excersise is");
			N[0].Exercise(FR);
			break;
		case 6:
		     System.out.println("Thank You !!!!");	
		     break;	
		}
		System.out.println("\nEnter yes to repeat again: ");
		choice=sc.next();
		}while(choice.equalsIgnoreCase("yes"));
	}
		catch(InputMismatchException e)
		{
			System.out.println("Erro "+e.getMessage());
		}
		catch(IOException e)
		{
			System.out.println("Error "+e.getMessage());
		}
		catch(UnderAgeException e)
		{
			System.out.println("error "+e.getMessage());
		}
		
	}
}


