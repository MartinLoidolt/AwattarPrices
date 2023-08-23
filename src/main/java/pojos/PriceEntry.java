package pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceEntry {
    private LocalDateTime start;
    private LocalDateTime end;
    private double basePrice;
    private String unit;
}
