package gameworld;

import java.util.Random;


/**
 * 
 * This class represents all objects that can take part in one on one combat.
 * 
 * @author Damian Kaye
 *
 */
public abstract class CombatObject extends AnimateObject{

	
	private int strength;
	private double dodgeChance;
	private double critChance = 0.15;
	private int maxHealth;
	private int currentHealth;
	
	public CombatObject(GridLocation gl, String name, String description, int health , int strength, double dodgeChance) {
		
		super(gl, name, description);
		this.strength = strength;
		this.maxHealth = health;
		this.dodgeChance = dodgeChance;
		this.currentHealth = maxHealth;
		
	}
	
	/**
	 * Checks to see whether any object has run out of health so is now classified as dead.
	 * 
	 * @return boolean stating if this object is dead or not.
	 */
	
	public abstract boolean isDead();
	
	/**
	 * Getter method that returns the health limit of their current health amount.
	 * @return int representing the max health amount
	 */
	
	public int getMaxHealth(){
		return maxHealth;
	}
	
	
	
	
	
	/**
	 * Sets the combat objects current health to current health - amount
	 * 
	 * @param int amount of health gained or lost
	 */
	public void setCurrentHealth(int amount){
		
		currentHealth = amount;
		if(currentHealth > maxHealth){
			currentHealth = maxHealth;
		}
	}
	
	/**
	 * Gets the combat objects current health 
	 * @return int representing the current health of the combat object
	 */
	
	public int getCurrentHealth(){
		return currentHealth;
	}
	
	/**
	 * Sets the health by the amount given
	 * 
	 * @param int amount to increase the combat objects max health by
	 */
	
	public void setMaxHealth(int amount){
		
		maxHealth = amount;
	}
	
	/**
	 * Increases the strength by the set amount 
	 * @param int amount to increase strength by
	 */
	
	public void setStrength(int amount){
		strength = amount;
	}
	
	/**
	 * Gets the current strength 
	 * @return int current strength
	 */
	public int getStrength(){
		return strength;
	}
	
	/**
	 * Gets the dodge chance 
	 * @return double, representing the percentage to dodge an attack
	 */
	public double getDodgeChance(){
		return dodgeChance;
	}
	
	/**
	 *  Every combat object has a chance to critical strike which is 
	 *  an attack that deals double damage
	 *  
	 * @return a boolean representing if the attack was a critical strike or not.
	 */
	
	public boolean criticalStrike(){

		Random rand = new Random();
		double chance = rand.nextDouble();
		
		if(chance <= critChance){
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * All combat objects have a set chance to dodge any attack, this method
	 * calculates if the attack lands or not based on the dodge chance of the 
	 * specific combat object.
	 * 
	 * @return a boolean representing if the attack landed or not.
	 */
	
	
	public boolean dodge(){
		
		Random rand = new Random();
		double chance = rand.nextDouble();
				
		
		if(chance <= getDodgeChance()){
			return true;
		}
		else{
			return false;
		}
	}
}
