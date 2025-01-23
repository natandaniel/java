package jdbc;

import org.springframework.data.annotation.Id;

public record Coffee(@Id String name, int supId, Float price, Integer sales, Integer total) {}
