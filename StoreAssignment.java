/**
 * Author: Jacques Gueye
 * Assignment: StoreAssignment 
 * Date: 05/08/21
 * Course: CS56 Adv Java (1791)
 * Description: Following application keeps track
 * of purchases for a furniture business
 * 
 */

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.ArrayList;

public class StoreAssignment {
    
    public static void main(String[] args) {
        FurnitureBusiness myBusiness=new FurnitureBusiness();
        
        Customer []customers=new Customer[3];
        customers[0]=new Customer(1,"A","Addy1");
        customers[1]=new Customer(2,"B","Addy2");
        customers[2]=new Customer(3,"C","Addy3");
        
        Furniture[] furnitures=new Furniture[5];
        furnitures[0]=new Table("Table1",1,0.1);
        furnitures[1]=new Table("Table2",2,0.2);
        furnitures[2]=new Recliner("Recliner1",1,0.3);
        furnitures[3]=new Recliner("Recliner2",1,0.4);
        furnitures[4]=new Chair("Chair1",1,0.5);
        
        myBusiness.purchase(customers[0], furnitures[0]);
        myBusiness.purchase(customers[0], furnitures[2]);
        myBusiness.purchase(customers[0], furnitures[4]);
        myBusiness.purchase(customers[1],furnitures[1]);
        myBusiness.purchase(customers[2], furnitures[3]);  
        /* Expect: "A has Table1, Chair1, Recliner1"
				   "B has Table2"
				   "C has Recliner2"
		*/
		//prints hasBought status for each customer
        for (Customer cus:customers){
            for(Furniture fur:furnitures){
                System.out.println(cus.getName()+ ", "+fur.getName()+", "+
                        myBusiness.hasBought(cus, fur));
            }
        }
		//prints furniture each customer bought
        for (Customer cus:customers){
            List<Furniture> furList=myBusiness.getPurchases(cus);
            System.out.print(cus.getName()+": ");
            for (Furniture fur:furList){
                System.out.print(fur.getName()+" ");
            }
            System.out.println();
        }
    } 
}
class Customer{
    private int id;
    private String name;
    private String address;
	
    Customer(){}
    Customer(int id,String name, String address){
        this.id=id;
        this.name=name;
        this.address=address;
    }
	
    public int getId(){return id;}
    public String getName(){return name;}
    public String getAddress(){return address;}
    public void setId(int id){this.id=id;}
    public void setName(String name){this.name=name;}
    public void setAddress(String address){this.address=address;}
	
    @Override
    public int hashCode(){
        int hash = 31;
        hash = 31 * hash + id;
        hash = 31 * hash + (name == null ? 0 : name.hashCode());
        hash = 31 * hash + (address == null ? 0 : address.hashCode());
        return hash;
    }
    @Override
    public boolean equals(Object o){
        if (o==this){
            return true;
        }    
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Customer custO=(Customer) o;
        return(Objects.equals(this.id,custO.id)&&Objects.equals(this.name,custO.name)
                &&Objects.equals(this.address,custO.address));
    }   
}

abstract class Furniture{
    private String name;
    private int modelNr;
    private double price;
	
    Furniture(){}
    Furniture(String name,int modelNr,double price){
        this.name=name;
        this.modelNr=modelNr;
        this.price=price;
    }
	
    public int getModerNr(){return modelNr;}
    public String getName(){return name;}
    public double getPrice(){return price;}
    public void setId(int modelNr){this.modelNr=modelNr;}
    public void setName(String name){this.name=name;}
    public void setAddress(double price){this.price=price;}

    @Override
    public int hashCode(){
        int hash =32;
        hash = 32 * hash + modelNr;
        hash = 32 * hash + (name == null ? 0 : name.hashCode());
        return hash;
    }
    @Override
    public boolean equals(Object o){
        if (o==this){
            return true;
        }   
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Furniture furnO=(Furniture) o;
        return(Objects.equals(this.modelNr,furnO.modelNr)&&Objects.equals(this.name,furnO.name));
    }  
}

class Table extends Furniture{
    Table(String name,int modelNr,double price){
        super(name,modelNr,price);
    }
}
class Recliner extends Furniture{
    Recliner(String name,int modelNr,double price){
        super(name,modelNr,price);
    }
}
class Chair extends Furniture{
    Chair(String name,int modelNr,double price){
        super(name,modelNr,price);
    }
}

class FurnitureBusiness{
    private HashMap<Customer,List<Furniture>> map=new HashMap<>();
    public void purchase(Customer c, Furniture f){
        if (map.containsKey(c)){//update customer
           map.get(c).add(f);
        }else{//create new customer
            List<Furniture> list=new ArrayList<>();
            map.put(c,list);
            map.get(c).add(f);
        }
    }
    public boolean hasBought(Customer c, Furniture f){
        return (map.containsKey(c)&&map.get(c).contains(f));
    }

    public List<Furniture> getPurchases(Customer c){
        return map.get(c);
    }
}
