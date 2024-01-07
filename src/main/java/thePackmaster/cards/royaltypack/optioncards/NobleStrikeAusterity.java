package thePackmaster.cards.royaltypack.optioncards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.GainGoldTextEffect;
import com.megacrit.cardcrawl.vfx.GainPennyEffect;
import thePackmaster.ThePackmaster;
import thePackmaster.cards.royaltypack.AbstractRoyaltyCard;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static thePackmaster.SpireAnniversary5Mod.makeID;

@AutoAdd.Ignore
public class NobleStrikeAusterity extends AbstractRoyaltyCard {

    public final static String ID = makeID("NobleStrikeAusterity");
    public final static int GOLD_GAINED = 5;

    public NobleStrikeAusterity(){
        super(ID, -2, CardType.STATUS, CardRarity.SPECIAL, CardTarget.SELF, ThePackmaster.Enums.PACKMASTER_RAINBOW,
                "anniv5Resources/images/cards/OptionAusterity.png");
        baseMagicNumber = magicNumber = 1;
    }

    @Override
    public void upp() {
        this.upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        onChoseThisOption();
    }

    @Override
    public void onChoseThisOption(){
        for (int i = 0; i < GOLD_GAINED; i++){
            AbstractDungeon.effectList.add(new GainPennyEffect(AbstractDungeon.player,
                    player.drawX, Settings.SAVED_HEIGHT,
                    player.drawX, player.drawY * 1.2f,
                    false));
        }
        this.addToBot(new GainGoldAction(GOLD_GAINED));
        AbstractDungeon.effectList.add(new GainGoldTextEffect(0));
        //CardCrawlGame.sound.play("GOLD_GAIN", 0.1F);
        this.addToBot(new DrawCardAction(player, magicNumber));
    }
}
