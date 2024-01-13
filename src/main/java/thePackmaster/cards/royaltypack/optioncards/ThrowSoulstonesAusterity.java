package thePackmaster.cards.royaltypack.optioncards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.BlizzardEffect;
import thePackmaster.ThePackmaster;
import thePackmaster.cards.royaltypack.AbstractRoyaltyCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

@AutoAdd.Ignore
public class ThrowSoulstonesAusterity extends AbstractRoyaltyCard {

    public final static String ID = makeID("ThrowSoulstonesAusterity");

    public ThrowSoulstonesAusterity(){
        super(ID, -2, CardType.STATUS, CardRarity.SPECIAL, CardTarget.ALL_ENEMY, ThePackmaster.Enums.PACKMASTER_RAINBOW,
                "anniv5Resources/images/cards/OptionAusterity.png");
    }

    @Override
    public void upp() {

    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        onChoseThisOption();
    }

    @Override
    public void onChoseThisOption(){

    }


}
