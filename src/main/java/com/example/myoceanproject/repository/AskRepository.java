package com.example.myoceanproject.repository;

import com.example.myoceanproject.entity.Ask;
import com.example.myoceanproject.type.AskCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AskRepository extends JpaRepository<Ask, Long> {

    @Query("select a.askCategory from Ask a where a.user.userId=:userId and a.askCategory=:categoryQuest")
    public AskCategory findcategoryByuserId(@Param("userId") Long userId,@Param("quest") AskCategory quest);
}
