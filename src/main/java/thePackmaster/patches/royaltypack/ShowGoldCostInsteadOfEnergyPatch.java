package thePackmaster.patches.royaltypack;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.vfx.FastCardObtainEffect;
import javassist.CtBehavior;
import thePackmaster.powers.royaltypack.HiredSupportPower;

@SpirePatch(
        clz = AbstractCard.class,
        method = "renderEnergy"
)

public class ShowGoldCostInsteadOfEnergyPatch {

    @SpireInsertPatch(
            locator= Locator.class
    )

    public static void ShowGoldCostInsteadOfEnergy(AbstractCard __instance, SpriteBatch __sb){
        if (AbstractDungeon.player != null && AbstractDungeon.player.hand.contains(__instance) &&
                AbstractDungeon.player.hasPower(HiredSupportPower.POWER_ID)) {
            Color costColor = Color.YELLOW;
            String text;
            if (__instance.cost == -1){
                text = "10 X";
            } else {
                text = Integer.toString(__instance.costForTurn*10);
            }
            FontHelper.cardEnergyFont_L.getData().setScale(__instance.drawScale);
            BitmapFont font = FontHelper.cardEnergyFont_L;

            FontHelper.renderRotatedText(__sb,
                    font,
                    text,
                    __instance.current_x, __instance.current_y, -132.0F * __instance.drawScale *
                            Settings.scale, 192.0F * __instance.drawScale * Settings.scale,
                    __instance.angle, false, costColor);


            return;
        }
    }


    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractCard.class, "getEnergyFont");
            int[] matches = LineFinder.findAllInOrder(ctMethodToPatch, finalMatcher);
            return new int[]{matches[0] + 1};
        }
    }
}
