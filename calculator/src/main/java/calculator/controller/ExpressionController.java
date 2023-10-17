package calculator.controller;
import calculator.service.CalculatorService.CalculateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calculate")
public class ExpressionController {

    private CalculateService calculateService;

    public ExpressionController(CalculateService calculateService) {
        this.calculateService = calculateService;
    }

    @GetMapping("/{expression}")
    public ResponseEntity<Object> getAns(@PathVariable(name="expression") String expression){
        return ResponseEntity.ok(calculateService.getAnswer(expression));
    }
}
