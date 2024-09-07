package thePackmaster.powers.royaltypack;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class HiredSupportPower extends AbstractPackmasterPower {

    public static final String POWER_ID = makeID("HiredSupportPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String DESCRIPTIONS[] = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public static final int ENERGY_TO_GOLD_CONVERSION = 10;

    public HiredSupportPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, true, owner, 1);
    }

    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        int goldAmount = card.cost * ENERGY_TO_GOLD_CONVERSION;
        if (goldAmount <= AbstractDungeon.player.gold){
            card.setCostForTurn(0);
            AbstractDungeon.player.loseGold(goldAmount);
            this.amount -= 1;
        }
    }
}
