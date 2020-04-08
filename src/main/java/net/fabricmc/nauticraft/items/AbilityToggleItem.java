package net.fabricmc.nauticraft.items;

import io.github.ladysnake.pal.AbilitySource;
import io.github.ladysnake.pal.Pal;
import io.github.ladysnake.pal.PlayerAbility;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class AbilityToggleItem extends Item {

    private PlayerAbility ability;
    private AbilitySource abilitySource;

    public AbilityToggleItem(Settings settings, PlayerAbility abilityId, Identifier abilitySourceId){
        super(settings);
        this.ability = abilityId;
        this.abilitySource = Pal.getAbilitySource(abilitySourceId);
    }


    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand){ //RightClick Event
        if(!world.isClient){
            if(abilitySource.grants(user, this.ability)) { //Toggle ability.
                abilitySource.revokeFrom(user, this.ability); //if it is, revoke it.
            }else{
                abilitySource.grantTo(user, this.ability); //otherwise grant ability
            }
            // Feedback message
            user.addChatMessage(new LiteralText("")
                    .append(new LiteralText(abilitySource.getId().toString()).styled(s -> s.setColor(Formatting.YELLOW)))
                    .append(abilitySource.grants(user, this.ability) ? new LiteralText(" added").styled(s -> s.setColor(Formatting.GREEN)) : new LiteralText(" removed").styled(s -> s.setColor(Formatting.RED)))
                    .append(" (")
                    .append(new LiteralText(this.ability.getId().toString()).styled(s -> s.setColor(Formatting.YELLOW)))
                    .append(" is ")
                    .append(ability.isEnabledFor(user) ? new LiteralText("enabled").styled(s -> s.setColor(Formatting.GREEN)) : new LiteralText("disabled").styled(s -> s.setColor(Formatting.RED)))
                    .append(")"), false);
        }
        return TypedActionResult.success(user.getStackInHand(hand));
    }

    /*
    public void inventoryTick(ItemStack stack, World world, PlayerEntity entity, int slot, boolean selected) {
        if(!world.isClient){
            if(slot > 35){
                abilitySource.grantTo(entity, this.ability);
            }else{
                abilitySource.revokeFrom(entity, this.ability);
            }
        }
    }
    */

}
