/**
 * Author: Jacques Gueye
 * Assignment 3: DeliveryTruck 
 * Date: 03/20/21
 * Course: CS56 Adv Java (1791)
 * Description: Program implements an application
 * that keeps track of package deliveries to consumers. Consists
 * of classes using inhertitance. Includes test driver.
 * 
 */
abstract class Mail {
	public String getAddress(){
		return address;
	}
	public void setAddress(String address){
		this.address=address;
	}
	private String address;
}
class Letter extends Mail{
	Letter (){}
	Letter(String contents){ this.contents=contents;}
	private String contents;
}
class Package extends Mail {
	Package(){}
	Package(Item[] items){
		this.items=items;
	}
	public Item[] getItems(){
		return items;
	}
	private Item[] items;
	
}
class Item {
	Item(){}
	Item(String name,String description){
		this.name=name;
		this.description=description;
	}
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name=name;
	}
	public String getDescription(){
		return this.description;
	}
	public void setDescription(String description){
	  this.description=description;
	}
	private String name;
	private String description;
}
class DeliveryTruck {
	public void load (Mail mail){
		for (int i=0;i<10; i++){
			if(this.mail[i]==null){
				this.mail[i]=mail;
				break;
			}
		}
	}
	private Mail[] mail=new Mail[10];
}
class DeliveryTruckProj { //test driver

    public static void main(String[] args) {
        //Item test
        Item[] items =new Item[2];
        items[0]=new Item("Item 1","Desc 1");
        items[1]=new Item();
        items[1].setName("Item 2");
        items[1].setDescription("Desc 2");
        System.out.println(items[0].getName() + " " +items[0].getDescription());//Item 1 Desc 1
        System.out.println(items[1].getName() + " " +items[1].getDescription());//Item 2 Desc 2
        
        //Package test
        Mail package1=new Package(items);
        for (Item item :((Package) package1).getItems()){
            System.out.println(item.getName()); //Item 1  Item 2
        }
        
        System.out.println("\nDeliveryTruck test: ");
        
        //Letter test
        Mail letter1 = new Letter();
        
        //Mail test
        letter1.setAddress("placeL");
        package1.setAddress("placeP");
        System.out.println(package1.getAddress());//placeP
        
        //Truck test
        DeliveryTruck Truck=new DeliveryTruck();
        Truck.load(package1);
        Truck.load(letter1);
        
                
    }
	
}
