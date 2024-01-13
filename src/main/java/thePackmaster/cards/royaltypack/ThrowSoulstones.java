package thePackmaster.cards.royaltypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.BlizzardEffect;
import thePackmaster.actions.royaltypack.TributeOrAusterityAction;
import thePackmaster.cards.royaltypack.optioncards.ThrowSoulstonesAusterity;
import thePackmaster.cards.royaltypack.optioncards.ThrowSoulstonesTribute;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ThrowSoulstones extends AbstractRoyaltyCard {

    public final static String ID = makeID("ThrowSoulstones");
    public final static int TRIBUTE_DAMAGE = 12;
    public final static int AUSTERITY_DAMAGE = 18;


    public ThrowSoulstones(){
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        baseDamage = damage = TRIBUTE_DAMAGE;
        baseSecondDamage = secondDamage = AUSTERITY_DAMAGE;
    }

    @Override
    public void upp() {
        this.upgradeDamage(3);
        this.upgradeSecondDamage(3);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (Settings.FAST_MODE) {
            this.addToBot(new VFXAction(new BlizzardEffect(damage, AbstractDungeon.getMonsters().shouldFlipVfx()), 0.25F));
        } else {
            this.addToBot(new VFXAction(new BlizzardEffect(damage, AbstractDungeon.getMonsters().shouldFlipVfx()), 0.5F));
        }
        int[] damageMatrix = DamageInfo.createDamageMatrix(this.baseDamage, false);
        DamageAllEnemiesAction dmgAll = new DamageAllEnemiesAction(AbstractDungeon.player, damageMatrix, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.NONE);
        addToBot(dmgAll);

        AbstractRoyaltyCard tsTributeChoiceCard = new ThrowSoulstonesTribute(this, secondDamage);
        AbstractRoyaltyCard tsAusterityChoiceCard = new ThrowSoulstonesAusterity();

        Wiz.atb(new TributeOrAusterityAction(tsTributeChoiceCard, tsAusterityChoiceCard));
    }
}
