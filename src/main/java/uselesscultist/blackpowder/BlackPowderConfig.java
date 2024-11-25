package uselesscultist.blackpowder;

import net.minecraftforge.common.ForgeConfigSpec;

public class BlackPowderConfig {
	
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;
    
    
    public static final ForgeConfigSpec.ConfigValue<Integer> FLINTLOCK_DAMAGE;
    public static final ForgeConfigSpec.ConfigValue<Integer> FLINTLOCK_RELOAD_TIME;
    public static final ForgeConfigSpec.ConfigValue<Integer> FLINTLOCK_QUICK_CHARGE_TIME;
    public static final ForgeConfigSpec.ConfigValue<Float> FLINTLOCK_INACCURACY;
    public static final ForgeConfigSpec.ConfigValue<Integer> BLUNDERBUSS_DAMAGE;
    public static final ForgeConfigSpec.ConfigValue<Integer> BLUNDERBUSS_RELOAD_TIME;
    public static final ForgeConfigSpec.ConfigValue<Integer> BLUNDERBUSS_QUICK_CHARGE_TIME;
    public static final ForgeConfigSpec.ConfigValue<Float> BLUNDERBUSS_INACCURACY;
    public static final ForgeConfigSpec.ConfigValue<Integer> RIFLE_DAMAGE;
    public static final ForgeConfigSpec.ConfigValue<Integer> RIFLE_RELOAD_TIME;
    public static final ForgeConfigSpec.ConfigValue<Integer> RIFLE_QUICK_CHARGE_TIME;
    public static final ForgeConfigSpec.ConfigValue<Float> RIFLE_INACCURACY;
    public static final ForgeConfigSpec.ConfigValue<Integer> MUSKET_DAMAGE;
    public static final ForgeConfigSpec.ConfigValue<Integer> MUSKET_RELOAD_TIME;
    public static final ForgeConfigSpec.ConfigValue<Integer> MUSKET_QUICK_CHARGE_TIME;
    public static final ForgeConfigSpec.ConfigValue<Float> MUSKET_INACCURACY;

    public static final ForgeConfigSpec.ConfigValue<Integer> FLINTLOCK_CARBINE_DAMAGE;
    public static final ForgeConfigSpec.ConfigValue<Integer> FLINTLOCK_CARBINE_RELOAD_TIME;
    public static final ForgeConfigSpec.ConfigValue<Integer> FLINTLOCK_CARBINE_QUICK_CHARGE_TIME;
    public static final ForgeConfigSpec.ConfigValue<Float> FLINTLOCK_CARBINE_INACCURACY;
    public static final ForgeConfigSpec.ConfigValue<Integer> BLUNDERBEHEMOTH_DAMAGE;
    public static final ForgeConfigSpec.ConfigValue<Integer> BLUNDERBEHEMOTH_RELOAD_TIME;
    public static final ForgeConfigSpec.ConfigValue<Integer> BLUNDERBEHEMOTH_QUICK_CHARGE_TIME;
    public static final ForgeConfigSpec.ConfigValue<Float> BLUNDERBEHEMOTH_INACCURACY;
    public static final ForgeConfigSpec.ConfigValue<Integer> RESOLUTE_RIFLE_DAMAGE;
    public static final ForgeConfigSpec.ConfigValue<Integer> RESOLUTERIFLE_RELOAD_TIME;
    public static final ForgeConfigSpec.ConfigValue<Integer> RESOLUTERIFLE_QUICK_CHARGE_TIME;
    public static final ForgeConfigSpec.ConfigValue<Float> RESOLUTERIFLE_INACCURACY;
    public static final ForgeConfigSpec.ConfigValue<Integer> BOUNDLESSMUSKET_DAMAGE;
    public static final ForgeConfigSpec.ConfigValue<Integer> BOUNDLESSMUSKET_RELOAD_TIME;
    public static final ForgeConfigSpec.ConfigValue<Integer> BOUNDLESSMUSKET_QUICK_CHARGE_TIME;
    public static final ForgeConfigSpec.ConfigValue<Float> BOUNDLESSMUSKET_INACCURACY;
    
    public static final ForgeConfigSpec.ConfigValue<Boolean> FUN_MODE;
    
    
    static {
    	BUILDER.push("Config for (FORGE) Black Powered mod");
    	
		 FLINTLOCK_DAMAGE = BUILDER.comment("Flintlock damage").define("Value", 16);
		 FLINTLOCK_RELOAD_TIME = BUILDER.comment("Flintlock reload time").define("Value", 50);
		 FLINTLOCK_QUICK_CHARGE_TIME = BUILDER.comment("Flintlock quick charge time").define("Value", 5);
		 FLINTLOCK_INACCURACY = BUILDER.comment("Flintlock inaccuracy").define("Value", 7f);
		 BLUNDERBUSS_DAMAGE = BUILDER.comment("Blunderbuss damage").define("Value", 4);
		 BLUNDERBUSS_RELOAD_TIME = BUILDER.comment("Blunderbuss reload time").define("Value", 160);
		 BLUNDERBUSS_QUICK_CHARGE_TIME = BUILDER.comment("Blunderbuss quick charge time").define("Value", 20);
		 BLUNDERBUSS_INACCURACY = BUILDER.comment("Blunderbuss inaccuracy").define("Value", 14f);
		 RIFLE_DAMAGE = BUILDER.comment("Rifle damage").define("Value", 22);
		 RIFLE_RELOAD_TIME = BUILDER.comment("Rifle reload time").define("Value", 160);
		 RIFLE_QUICK_CHARGE_TIME = BUILDER.comment("Rifle quick charge time").define("Value", 20);
		 RIFLE_INACCURACY = BUILDER.comment("Rifle inaccuracy").define("Value", 2f);
		 MUSKET_DAMAGE = BUILDER.comment("Musket damage").define("Value", 26);
		 MUSKET_RELOAD_TIME = BUILDER.comment("Musket reload time").define("Value", 100);
		 MUSKET_QUICK_CHARGE_TIME = BUILDER.comment("Musket quick charge time").define("Value", 10);
		 MUSKET_INACCURACY = BUILDER.comment("Musket inaccuracy").define("Value", 4f);

		 FLINTLOCK_CARBINE_DAMAGE = BUILDER.comment("Flintlock damage").define("Value", 12);
		 FLINTLOCK_CARBINE_RELOAD_TIME = BUILDER.comment("Flintlock damage").define("Value", 3);
		 FLINTLOCK_CARBINE_QUICK_CHARGE_TIME = BUILDER.comment("Flintlock damage").define("Value", 1);
		 FLINTLOCK_CARBINE_INACCURACY = BUILDER.comment("Flintlock damage").define("Value", 7f);
		 BLUNDERBEHEMOTH_DAMAGE = BUILDER.comment("Flintlock damage").define("Value", 4);
		 BLUNDERBEHEMOTH_RELOAD_TIME = BUILDER.comment("Flintlock damage").define("Value", 320);
		 BLUNDERBEHEMOTH_QUICK_CHARGE_TIME = BUILDER.comment("Flintlock damage").define("Value", 40);
		 BLUNDERBEHEMOTH_INACCURACY = BUILDER.comment("Flintlock damage").define("Value", 28f);
		 RESOLUTE_RIFLE_DAMAGE = BUILDER.comment("Flintlock damage").define("Value", 22);
		 RESOLUTERIFLE_RELOAD_TIME = BUILDER.comment("Flintlock damage").define("Value", 240);
		 RESOLUTERIFLE_QUICK_CHARGE_TIME = BUILDER.comment("Flintlock damage").define("Value", 40);
		 RESOLUTERIFLE_INACCURACY = BUILDER.comment("Flintlock damage").define("Value", 0f);
		 BOUNDLESSMUSKET_DAMAGE = BUILDER.comment("Flintlock damage").define("Value", 318);
		 BOUNDLESSMUSKET_RELOAD_TIME = BUILDER.comment("Flintlock damage").define("Value", 200);
		 BOUNDLESSMUSKET_QUICK_CHARGE_TIME = BUILDER.comment("Flintlock damage").define("Value", 20);
		 BOUNDLESSMUSKET_INACCURACY = BUILDER.comment("Flintlock damage").define("Value", 4f);
    	
		 FUN_MODE = BUILDER.comment("FUN_MODE (Do nothing)").define("Value", false);
		 
    	BUILDER.pop();
    	
    	SPEC = BUILDER.build();
    }
}
