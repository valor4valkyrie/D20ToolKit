package com.d20.model;

public interface Skill {

    void setSkillName(String name);
    String getSkillName();

    void setSkillKeyAbility(String keyAbility);
    String getSkillKeyAbility();

    int getSkillTotal();

    void setSkillAbility(int skillAbility);
    int getSkillAbility();

    void setSkillRank(int skillRank);
    int getSkillRank();

    void setSkillMisc(int skillMisc);
    int getSkillMisc();


}
