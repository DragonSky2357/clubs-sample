package com.example.Clubs.club.entity;

import com.example.Clubs.club.dto.request.UpdateClubRequest;
import com.example.Clubs.common.entity.BaseEntity;
import com.example.Clubs.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Builder
@AllArgsConstructor
@Where(clause = "is_active = true")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE club SET is_active = false WHERE id = ?")
public class Club extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 300)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Member owner;

    private boolean isActive;

    public Club update(UpdateClubRequest request){
        if (request.getTitle() != null && !request.getTitle().isBlank()) {
            this.title = request.getTitle();
        }
        if (request.getDescription() != null && !request.getDescription().isBlank()){
            this.description = request.getDescription();
        }
        return this;
    }

}
