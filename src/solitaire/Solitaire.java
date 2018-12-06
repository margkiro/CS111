package solitaire;

import java.io.IOException;
import java.util.Scanner;
import java.util.Random;

/**
 * This class implements a simplified version of Bruce Schneier's Solitaire Encryption algorithm.
 * 
 * @author RU NB CS112
 */
public class Solitaire {
	
	/**
	 * Circular linked list that is the deck of cards for encryption
	 */
	CardNode deckRear;
	
	/**
	 * Makes a shuffled deck of cards for encryption. The deck is stored in a circular
	 * linked list, whose last node is pointed to by the field deckRear
	 */
	public void makeDeck() {
		// start with an array of 1..28 for easy shuffling
		int[] cardValues = new int[28];
		// assign values from 1 to 28
		for (int i=0; i < cardValues.length; i++) {
			cardValues[i] = i+1;
		}
		
		// shuffle the cards
		Random randgen = new Random();
 	        for (int i = 0; i < cardValues.length; i++) {
	            int other = randgen.nextInt(28);
	            int temp = cardValues[i];
	            cardValues[i] = cardValues[other];
	            cardValues[other] = temp;
	        }
	     
	    // create a circular linked list from this deck and make deckRear point to its last node
	    CardNode cn = new CardNode();
	    cn.cardValue = cardValues[0];
	    cn.next = cn;
	    deckRear = cn;
	    for (int i=1; i < cardValues.length; i++) {
	    	cn = new CardNode();
	    	cn.cardValue = cardValues[i];
	    	cn.next = deckRear.next;
	    	deckRear.next = cn;
	    	deckRear = cn;
	    }
	}
	
	/**
	 * Makes a circular linked list deck out of values read from scanner.
	 */
	public void makeDeck(Scanner scanner) 
	throws IOException {
		CardNode cn = null;
		if (scanner.hasNextInt()) {
			cn = new CardNode();
		    cn.cardValue = scanner.nextInt();
		    cn.next = cn;
		    deckRear = cn;
		}
		while (scanner.hasNextInt()) {
			cn = new CardNode();
	    	cn.cardValue = scanner.nextInt();
	    	cn.next = deckRear.next;
	    	deckRear.next = cn;
	    	deckRear = cn;
		}
	}
	
	/**
	 * Implements Step 1 - Joker A - on the deck.
	 */
	public void jokerA() {
		
		if(deckRear == null){
			return;
		}
		CardNode temp = deckRear.next;
		CardNode prev = deckRear;
		if(deckRear.cardValue == 27){
			do{
				temp = temp.next;
			}while(temp.next != deckRear);
			prev = temp;
			CardNode temp2 = deckRear;
			CardNode temp3 = deckRear.next.next;
			CardNode temp4 = deckRear.next;
			prev.next = temp4;
			deckRear = temp4;
			deckRear.next = temp2;
			deckRear.next.next = temp3;
			printList(deckRear);
			return;
		}
		printList(deckRear);
		for(temp = deckRear.next; temp!= null; temp = temp.next){
			if(temp.next.cardValue == 27){
				CardNode n = new CardNode();
				n = temp.next;
				temp.next = temp.next.next;
				n.next = temp.next.next;
				temp.next.next = n;
				printList(deckRear);
				return;
			}
		}
		
		// COMPLETE THIS METHOD
	}
	
	/**
	 * Implements Step 2 - Joker B - on the deck.
	 */
	void jokerB() {
		
		if(deckRear == null){
			return;
		}
		CardNode prev = deckRear;
		printList(deckRear);
		CardNode temp = deckRear.next;
			if(deckRear.cardValue == 28){
				do{
					temp = temp.next;
				}while(temp.next != deckRear);
				prev = temp;
				CardNode temp2 = deckRear;
				CardNode temp3 = deckRear.next.next;
				CardNode temp4 = deckRear.next;
				CardNode temp5 = deckRear.next.next.next;
				prev.next = temp4;
				deckRear = temp4;
				deckRear.next = temp3;
				deckRear.next.next = temp2;
				deckRear.next.next.next = temp5;
				printList(deckRear);
				return;
			}
			do{
				temp = temp.next;
			}while(temp.next.next != deckRear);
			if(temp.next.cardValue == 28){
				prev = temp;
				CardNode temp2 = deckRear;
				CardNode temp3 = deckRear.next.next;
				CardNode temp4 = deckRear.next;
				CardNode temp5 = prev.next;
				prev.next = temp2;
				prev.next.next = temp4;
				deckRear = temp4;
				deckRear.next = temp5;
				deckRear.next.next = temp3;
				
				printList(deckRear);
				return;
			}
		for(temp = deckRear.next; temp!= null; temp = temp.next){
			if(temp.next.cardValue == 28){
				CardNode n = new CardNode();
				n = temp.next;
				temp.next = temp.next.next;
				n.next = temp.next.next.next;
				temp.next.next.next = n;
				printList( deckRear);
				return;
			}
			
		}
		
	    // COMPLETE THIS METHOD
	}
	
	/**
	 * Implements Step 3 - Triple Cut - on the deck.
	 */
	void tripleCut() {
		printList(deckRear);
		CardNode a = deckRear.next;
		CardNode ptr = deckRear.next;
		if(ptr.cardValue == 27 || ptr.cardValue == 28){
			do{
				ptr = ptr.next;
			}while(ptr.cardValue != 27 && ptr.cardValue !=28);
			deckRear = ptr;
			return;
			
		}
		while(ptr.next.cardValue != 27 && ptr.next.cardValue !=28){
			ptr = ptr.next;
		}
		CardNode n = ptr;
		CardNode j1 = ptr.next;
		ptr = ptr.next.next;
		while(ptr.cardValue != 27 && ptr.cardValue != 28){
			ptr = ptr.next;
		}
		CardNode j2 = ptr;
		if(j2.next == deckRear.next){
			deckRear = n;
			return;
		}
		CardNode c = ptr.next;
		while(ptr != deckRear){
			ptr = ptr.next;
		}
		CardNode s = ptr;
		s.next = j1;
		j2.next = a;
		n.next = c;
		deckRear = n;
		printList(deckRear);
		
		// COMPLETE THIS METHOD
	}
	
	/**
	 * Implements Step 4 - Count Cut - on the deck.
	 */
	void countCut() {
		int n = deckRear.cardValue;
		if(n == 28 || n == 27)
			return;
		printList(deckRear);
		CardNode m = deckRear;
		CardNode p = deckRear.next;
		for(int c = 0; c<n; c++){
			m = m.next;
		}
		CardNode b = m;
		CardNode s = m.next;
		while(m.next!=deckRear){
			m = m.next;
		}
		m.next = p;
		b.next = deckRear;
		deckRear.next = s;
		printList(deckRear);
		
		// COMPLETE THIS METHOD
	}
	
	/**
	 * Gets a key. Calls the four steps - Joker A, Joker B, Triple Cut, Count Cut, then
	 * counts down based on the value of the first card and extracts the next card value 
	 * as key. But if that value is 27 or 28, repeats the whole process (Joker A through Count Cut)
	 * on the latest (current) deck, until a value less than or equal to 26 is found, which is then returned.
	 * 
	 * @return Key between 1 and 26
	 */
	int getKey() {
		// COMPLETE THIS METHOD
		// THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE METHOD COMPILE
		jokerA();
		
		jokerB();
		
		tripleCut();
		
		countCut();
		
		CardNode n = deckRear.next;
		int p = n.cardValue;
		if(p == 28)
			p = 27;
		int s;
		for(int i = 0; i<p; i++){
			n = n.next;	
		}
		s = n.next.cardValue;
		if(s==27 || s== 28){
			s = getKey();
		}
		//printList(deckRear);
		System.out.println(s);
		return s;
	}
	
	/**
	 * Utility method that prints a circular linked list, given its rear pointer
	 * 
	 * @param rear Rear pointer
	 */
	private static void printList(CardNode rear) {
		if (rear == null) { 
			return;
		}
		System.out.print(rear.next.cardValue);
		CardNode ptr = rear.next;
		do {
			ptr = ptr.next;
			System.out.print("," + ptr.cardValue);
		} while (ptr != rear);
		System.out.println("\n");
	}

	/**
	 * Encrypts a message, ignores all characters except upper case letters
	 * 
	 * @param message Message to be encrypted
	 * @return Encrypted message, a sequence of upper case letters only
	 */
	public String encrypt(String message) {	
		// COMPLETE THIS METHOD
		String enMessage = "";
		for(int i = 0; i<message.length(); i++){
			char m = message.charAt(i);
			if(!Character.isLetter(m)){
				continue;
			}
			m = Character.toUpperCase(m);
			int k = getKey();
			m = (char) (k+m);
				if((int)m >90){
					m = (char)(m-26);
				}
			enMessage = enMessage + m;
			
		}
		
		return enMessage;
	    // THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE METHOD COMPILE
	}
	
	/**
	 * Decrypts a message, which consists of upper case letters only
	 * 
	 * @param message Message to be decrypted
	 * @return Decrypted message, a sequence of upper case letters only
	 */
	public String decrypt(String message) {
		String ogMessage = "";
		for(int i = 0; i<message.length(); i++){
			char l = message.charAt(i);
			if(!Character.isLetter(l)){
				continue;
			}
			l = Character.toUpperCase(l);
			int de = getKey();
			l = (char)(l-de);
			if((int)l<65){
				l = (char)(l+26);
			}
			ogMessage = ogMessage + l;
		}
		// COMPLETE THIS METHOD
	    // THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE METHOD COMPILE
	    return ogMessage;
	}
}
