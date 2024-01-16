package thePackmaster.cards.royaltypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.GainGoldTextEffect;
import thePackmaster.actions.royaltypack.TributeOrAusterityAction;
import thePackmaster.cards.royaltypack.optioncards.NobleStrikeAusterity;
import thePackmaster.cards.royaltypack.optioncards.NobleStrikeTribute;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class NobleStrike extends AbstractRoyaltyCard {

    public final static String ID = makeID("NobleStrike");
    public final static int GOLD_GAINED = 5;

    public NobleStrike(){
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.tags.add(CardTags.STRIKE);
        baseDamage = 6;
        baseMagicNumber = magicNumber = 1;
    }

    @Override
    public void upp() {
        this.upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        dmg(abstractMonster, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        this.addToBot(new GainGoldAction(GOLD_GAINED));
        AbstractDungeon.effectList.add(new GainGoldTextEffect(GOLD_GAINED));
        CardCrawlGame.sound.play("GOLD_GAIN", 0.1F);
        this.addToBot(new DrawCardAction(AbstractDungeon.player, magicNumber));

        AbstractRoyaltyCard nsTributeChoiceCard = new NobleStrikeTribute();
        AbstractRoyaltyCard nsAusterityChoiceCard = new NobleStrikeAusterity();
        for (int i = 0; i < magicNumber - 1; i++){
            nsTributeChoiceCard.upgrade();
        }

        Wiz.atb(new TributeOrAusterityAction(nsTributeChoiceCard, nsAusterityChoiceCard));
        //Also, add sfx and vfx sounds from Buriedbornes. Eventually.
    }
}
