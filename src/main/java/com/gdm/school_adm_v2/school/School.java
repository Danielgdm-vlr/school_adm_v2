package com.gdm.school_adm_v2.school;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gdm.school_adm_v2.address.Address;
import com.gdm.school_adm_v2.school_details.SchoolDetails;
import com.gdm.school_adm_v2.user.User;
import com.gdm.school_adm_v2.util.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity(name = "school")
@Table(name = "school")

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "getSchoolsWhereTeaches",
                query = "select " +
                        "    distinct(s.id) as idSchool " +
                        "from school_sytch ssytch " +
                        "         inner join school s on s.id = ssytch.id_school " +
                        "         inner join school_sytch_sytch sss on ssytch.id = sss.id_school_sytch " +
                        "         inner join sytch s2 on s2.id = sss.id_sytch " +
                        "         inner join school_year sy on sy.id = s2.id_school_year and sy.year = ?1 " +
                        "         inner join sytchs_tch st on s2.id = st.id_sytch " +
                        "         inner join tch t2 on t2.id = st.id_tch " +
                        "         inner join teacher t on t.id = t2.id_teacher and t.cnp = ?2 " +
                        "         inner join tch_ch tc on t2.id = tc.id_tch " +
                        "         inner join course_hours ch on ch.id = tc.id_ch " +
                        "         inner join course c on c.id = ch.id_course;",
                resultSetMapping = "getSchoolsWhereTeachesResultSet"
        )
})
@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "getSchoolsWhereTeachesResultSet",
                classes = @ConstructorResult(
                        targetClass = SchoolDTO.class,
                        columns = @ColumnResult(name = "idSchool", type = Long.class)
                )
        )
})

@NoArgsConstructor
@Getter
@Setter
@ToString
public class School extends BaseEntity {

    @OneToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST
    })
    @JoinColumn(name = "id_school_details", referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private SchoolDetails schoolDetails;

    // intai de salvat adresa, si in scoala o cauti cu service.getBy
    @OneToOne(cascade = {
            CascadeType.MERGE
    })
    @JoinColumn(name = "id_address", referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Address address;

    @OneToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST
    })
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;

    public School(
            boolean active,
            SchoolDetails schoolDetails,
            Address address
    ) {
        super(active);

        this.schoolDetails = schoolDetails;
        this.address = address;
    }

    public School(
            boolean active,
            SchoolDetails schoolDetails,
            Address address,
            User user) {

        super(active);

        this.schoolDetails = schoolDetails;
        this.address = address;
        this.user = user;
    }
}
