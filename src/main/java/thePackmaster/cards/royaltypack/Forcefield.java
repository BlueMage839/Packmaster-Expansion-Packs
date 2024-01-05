package thePackmaster.cards.royaltypack;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.royaltypack.TributeOrAusterityAction;
import thePackmaster.cards.royaltypack.optioncards.ForcefieldAusterity;
import thePackmaster.cards.royaltypack.optioncards.ForcefieldTribute;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Forcefield extends AbstractRoyaltyCard {

    public final static String ID = makeID("Forcefield");
    public final static int TRIBUTE_MAXHP = 7;
    public final static int AUSTERITY_MAXHP = 8;

    public Forcefield() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = AUSTERITY_MAXHP;
        baseSecondMagic = secondMagic = TRIBUTE_MAXHP;
    }

    @Override
    public void upp() {
        this.upgradeMagicNumber(3);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        Wiz.atb(new DiscardAction(abstractPlayer, abstractPlayer, 1, true));
        Wiz.atb(new AddTemporaryHPAction(AbstractDungeon.player,
                AbstractDungeon.player, this.magicNumber));


        AbstractRoyaltyCard forcefieldTributeChoiceCard = new ForcefieldTribute(secondMagic);
        AbstractRoyaltyCard forcefieldAusterityChoiceCard = new ForcefieldAusterity();

        Wiz.atb(new TributeOrAusterityAction(forcefieldTributeChoiceCard, forcefieldAusterityChoiceCard));
    }
}
