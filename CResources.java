class CResources extends CBase{
  private int maxHP = 10, HP, baseDefense = 0, baseAttack =5, baseSpeed;
  private int currDefense, currAttack, currSpeed;
  
  public void equipArmor(InventoryItem i){
  //  if(i == null) return;
    currDefense =baseDefense + i.modifier;
  }
  
  public void unequipArmor(InventoryItem i){
   // if(i == null) return;
    currDefense=baseDefense;
  }
  
  public void equipWep(InventoryItem i){
    currAttack =baseAttack + i.modifier;
    currSpeed = (int)(baseSpeed*i.speedMod);
  }
  
  public void unequipWep(InventoryItem i){
    currAttack=baseAttack;
    currSpeed = baseSpeed;
  }
  public void damage(int damage){
    System.out.print("The HP was " +HP);
    HP -= (int)(100f/(currDefense+100)*damage);
    if(HP <= 0) owner.kill();
    System.out.println(" but now it's " +HP);
  }
  public int getHP(){return HP;}
  public int getMaxHP(){return maxHP;}
  public int getDefense(){return currDefense;}
  public int getAttack(){return currAttack;}
  
  public CResources(Entity o){
    super(o);
    currDefense = baseDefense;
    currAttack = baseAttack;
    currSpeed = baseSpeed;
    HP = maxHP;
  }
}