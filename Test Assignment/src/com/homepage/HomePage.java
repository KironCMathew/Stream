package com.homepage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.Stream;

import com.bean.X;
import com.bean.Y;


public class HomePage {

	public static void main(String[] args) throws IOException {
		
		String fileName1 = "C:\\Users\\kiron\\eclipse-workspace\\Test Assignment\\src\\com\\service\\X.txt";
		String fileName2 = "C:\\Users\\kiron\\eclipse-workspace\\Test Assignment\\src\\com\\service\\Y.txt";
		
		Stream<String> stream1 = Files.lines(Paths.get(fileName1));
		Stream<String> stream2 = Files.lines(Paths.get(fileName2));
		
		String[] stringArray = stream1.toArray(size -> new String[size]);
		String[] stringArray2 = stream2.toArray(size -> new String[size]);
		 
		 List<String> exact = new ArrayList<>();
	     List<String> weak = new ArrayList<>();
	     List<String> break1 = new ArrayList<>();
	        
		
		X[] data = new X[10];
		for(int i =0;i< stringArray.length; i++)
		{
			data[i] = new X();
		}
		
		
	  for(int i =0; i< stringArray.length ; i++ ) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" d-MMM-yyyy");
		String[] subArray =  stringArray[i].split(";");
		data[i].setAccountId(subArray[0]);
		data[i].setTransactionId(subArray[1]);
		data[i].setPostingDate(LocalDate.parse(subArray[2],formatter));
		data[i].setAmount(subArray[3]);
    	}
	  
	  Y[] data1 = new Y[10];
		
		for(int i =0;i< stringArray.length; i++)
		{
			data1[i] = new Y();
		}
		
		
	  for(int i =0; i< stringArray.length ; i++ ) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" d-MMM-yyyy");
		String[] subArray =  stringArray2[i].split(";");
		data1[i].setAccountId(subArray[0]);
		data1[i].setTransactionId(subArray[1]);
		data1[i].setPostingDate(LocalDate.parse(subArray[2],formatter));
		data1[i].setAmount(subArray[3]);
  	}
	  
	  // filtering Transaction ID
		BiPredicate<X, Y> filterTrans = (x, y) -> {
            return x.getTransactionId().contains(y.getTransactionId());
        };
        
      // filtering date  
		BiPredicate<X, Y> filterdate = (x, y) -> {
		Period period = Period.between(x.getPostingDate(),y.getPostingDate());			
	 if(((x.getPostingDate().getDayOfWeek()) ==DayOfWeek.SATURDAY ) && y.getPostingDate().getDayOfWeek() == DayOfWeek.SUNDAY)
			{
				return(period.getDays() == 0);
			}
		else {	
		    return (period.getDays() < 1);
			 }
        };		
         
           BiPredicate<X, Y> filterAmount = (x, y) -> {
        	double d1=Double.parseDouble(x.getAmount());
        	double d2=Double.parseDouble(y.getAmount());
           	if(d1 > d2) {
           	return (d1-d2 <= 0.01);
           	}
           	else if(d2 >d1) {
           	return (d2-d1 <= 0.01);
           	}
           	else 
           	return (d2-d1 == 0);
        };	              
     
        for(int q =0; q< 8;q++) {
        BiPredicate<X,Y> combineAll = filterTrans.and(filterAmount).and(filterdate);
        boolean result= combineAll.test(data[q], data1[q]);
         if(result == true)
         {
        	 exact.add(data[q].getAccountId());
        	 exact.add(data1[q].getAccountId());
         }
        else {
         BiPredicate<X, Y> combine = filterTrans.negate().or(filterAmount);         
         boolean result1 = combine.test(data[q], data1[q]);
         if(result1 == true) {
             break1.add(data[q].getAccountId());
             break1.add(data1[q].getAccountId());
        }
         else {
        	weak.add(data[q].getAccountId());
        	weak.add(data1[q].getAccountId());
      		  }
        }
        }
        System.out.println("Exact Matches");
        for(String e: exact)
        {
        	System.out.print(e+ " ");
        }
        System.out.println();
        System.out.println("Weak Matches");
        for(String e: weak)
        {
        	System.out.print(e + " ");
        }
        System.out.println();
        System.out.println("Break Matches");
        for(String e: break1)
        {
        	System.out.print(e + " ");
        }
	}

}
