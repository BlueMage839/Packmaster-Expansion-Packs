package thePackmaster.cards.royaltypack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import thePackmaster.actions.royaltypack.TributeOrAusterityAction;
import thePackmaster.cards.royaltypack.optioncards.WillpowerAusterity;
import thePackmaster.cards.royaltypack.optioncards.WillpowerTribute;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Willpower extends AbstractRoyaltyCard {

    public final static String ID = makeID("Willpower");

    public Willpower(){
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.isInnate = false;
    }

    @Override
    public void upp() {
        this.isInnate = true;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        Wiz.atb(new ApplyPowerAction(AbstractDungeon.player,
                AbstractDungeon.player,
                new ArtifactPower(AbstractDungeon.player, 1)));

        AbstractRoyaltyCard willTributeChoiceCard = new WillpowerTribute();
        AbstractRoyaltyCard willAusterityChoiceCard = new WillpowerAusterity();

        Wiz.atb(new TributeOrAusterityAction(willTributeChoiceCard, willAusterityChoiceCard));
    }
}
