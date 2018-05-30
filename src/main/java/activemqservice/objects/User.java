package activemqservice.objects;

import lombok.*;

import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class User {

    @Size(min = 4, max = 15, message = "Text should be within 4-15 characters.")
    private String firstName;

    @Size(min = 4, max = 15, message = "Text should be within 4-15 characters.")
    private String lastName;

}
