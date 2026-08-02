-- R__product_overview.sql (repeatable) =====
CREATE OR REPLACE VIEW product_overview AS
SELECT c.name AS category, COUNT(p.id) AS product_count
FROM categories c LEFT JOIN products p ON p.category_id = c.id
GROUP BY c.name;