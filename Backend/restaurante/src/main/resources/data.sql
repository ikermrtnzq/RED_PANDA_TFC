-- 1. USUARIOS Y CARTA
-- 1. USUARIOS Y CARTA
-- Las contraseñas ahora están hasheadas con BCrypt
INSERT INTO usuarios (id, nombre, email, pwd) VALUES
(1, 'admin', 'admin@example.com', '$2a$10$YusoqaJ.S.7o586llMOx4ufRd9LIldO6AZ0PmpX9aphpbm8a0J5A.'),
(2, 'cliente1', 'cliente1@example.com', '$2a$10$KeAZwQj3/1DHOaKvqfftXOtr9xbl5LLg0Ny3BrBXUCaa6yzFSCJ4C');
INSERT INTO carta (id) VALUES (1);

-- 2. INGREDIENTES (Incluyendo los nuevos para carnes, pescados, etc.)
INSERT INTO ingrediente (id, nombre) VALUES
(1, 'Arroz'), (2, 'Salmón'), (3, 'Atún'), (4, 'Alga Nori'),
(5, 'Fideos'), (6, 'Caldo'), (7, 'Soja'), (8, 'Langostino'),
(9, 'Aguacate'), (10, 'Anguila'), (11, 'Huevo'), (12, 'Cerdo'),
(13, 'Miso'), (14, 'Cebollino'), (15, 'Sésamo'),
(16, 'Pulpo'), (17, 'Panko'), (18, 'Ternera'), (19, 'Pollo'),
(20, 'Té Matcha'), (21, 'Fresa'), (22, 'Chocolate'), (23, 'Salsa Hoisin'),
(24, 'Tofu'), (25, 'Bacalao');

-- 3. PRODUCTOS
INSERT INTO sushi (id, nombre, cantidad, descripcion, numero_piezas, precio, imagen, id_carta) VALUES
(1, 'Sushi Salmón', 3, 'Salmón fresco con arroz', 8, 10.5, '/assets/carta/Shushis/sushi_salmon.webp', 1),
(2, 'Sushi Atún', 4, 'Atún rojo con arroz', 8, 11.0, '/assets/carta/Shushis/sushi_atun.webp', 1),
(3, 'Dragon Roll', 2, 'Langostino tempurizado y aguacate', 8, 14.5, '/assets/carta/Shushis/dragon_roll.webp', 1),
(4, 'Nigiri Unagi', 5, 'Anguila braseada sobre arroz', 2, 6.5, '/assets/carta/Shushis/nigiri_unagi.webp', 1),
(5, 'California Roll', 3, 'Cangrejo, pepino y aguacate', 8, 9.5, '/assets/carta/Shushis/california.webp', 1),
(6, 'Futomaki Veggie', 2, 'Rollo grande de verduras variadas', 6, 8.0, '/assets/carta/Shushis/futomaki.webp', 1),
(7, 'Spicy Tuna', 4, 'Atún picante picado en nido de alga', 2, 7.0, '/assets/carta/Shushis/spicy_tuna.webp', 1);

INSERT INTO ramen (id, nombre, cantidad, precio, imagen, id_carta, descripcion) VALUES
(1, 'Ramen Tonkotsu', 1, 13.5, '/assets/carta/Ramens/ramen_tonkotsu.webp', 1, 'Caldo de cerdo cremoso con fideos finos'),
(2, 'Ramen Miso', 1, 12.0, '/assets/carta/Ramens/ramen_miso.webp', 1, 'Caldo a base de soja fermentada y verduras'),
(3, 'Shoyu Ramen', 1, 12.5, '/assets/carta/Ramens/shoyu_ramen.webp', 1, 'Caldo ligero de pollo y soja con huevo'),
(4, 'Tantanmen', 1, 14.0, '/assets/carta/Ramens/tantanmen.webp', 1, 'Ramen picante de sésamo y carne picada'),
(5, 'Shio Ramen', 1, 11.5, '/assets/carta/Ramens/shio_ramen.webp', 1, 'Caldo claro a base de sal marina'),
(6, 'Veggie Ramen', 1, 12.0, '/assets/carta/Ramens/veggie_ramen.webp', 1, 'Caldo de verduras, setas y tofu'),
(7, 'Black Garlic Ramen', 1, 15.0, '/assets/carta/Ramens/black_garlic.webp', 1, 'Caldo intenso con aceite de ajo negro');

INSERT INTO bebida (id, nombre, cantidad, precio, imagen, id_carta, descripcion) VALUES
(1, 'Té Verde', 1, 2.5, '/assets/carta/Bebidas/te_verde.webp', 1, 'Té verde japonés tradicional caliente'),
(2, 'Ramune', 1, 3.0, '/assets/carta/Bebidas/ramune.webp', 1, 'Refresco gaseoso japonés original'),
(3, 'Cerveza Sapporo', 1, 4.5, '/assets/carta/Bebidas/sapporo.webp', 1, 'Cerveza premium japonesa'),
(4, 'Sake Caliente', 1, 6.0, '/assets/carta/Bebidas/sake.webp', 1, 'Licor de arroz tradicional'),
(5, 'Agua Mineral', 1, 1.5, '/assets/carta/Bebidas/agua.webp', 1, 'Agua sin gas 500ml'),
(6, 'Calpis', 1, 3.5, '/assets/carta/Bebidas/calpis.webp', 1, 'Bebida láctea refrescante'),
(7, 'Cerveza Asahi', 1, 4.5, '/assets/carta/Bebidas/asahi.webp', 1, 'Cerveza japonesa seca');

INSERT INTO entrante (id, nombre, cantidad, descripcion, precio, imagen, id_carta) VALUES
(1, 'Gyozas', 4, 'Empanadillas japonesas', 5.5, '/assets/carta/Entrantes/gyozas.webp', 1),
(2, 'Tempura', 4, 'Gambas fritas', 4.0, '/assets/carta/Entrantes/tempura.webp', 1),
(3, 'Edamame', 1, 'Vainas de soja al vapor', 3.5, '/assets/carta/Entrantes/edamame.webp', 1),
(4, 'Wakame', 1, 'Ensalada de algas aliñadas', 4.5, '/assets/carta/Entrantes/wakame.webp', 1),
(5, 'Takoyaki', 4, 'Bolitas de pulpo fritas', 6.5, '/assets/carta/Entrantes/takoyaki.webp', 1),
(6, 'Karaage', 1, 'Pollo frito al estilo japonés', 7.0, '/assets/carta/Entrantes/karaage.webp', 1),
(7, 'Sopa Miso', 1, 'Sopa tradicional con tofu', 3.0, '/assets/carta/Entrantes/miso_soup.webp', 1);

INSERT INTO carne (id, nombre, cantidad, precio, imagen, id_carta, descripcion) VALUES
(1, 'Wagyu A5', 1, 45.0, '/assets/carta/Carnes/wagyu.webp', 1, 'Corte de buey japonés de máxima calidad'),
(2, 'Pollo Teriyaki', 1, 12.5, '/assets/carta/Carnes/pollo_teriyaki.webp', 1, 'Pollo a la parrilla con salsa dulce'),
(3, 'Tonkatsu', 1, 11.0, '/assets/carta/Carnes/tonkatsu.webp', 1, 'Lomo de cerdo empanado en panko'),
(4, 'Sukiyaki', 1, 18.0, '/assets/carta/Carnes/sukiyaki.webp', 1, 'Ternera cocinada a fuego lento con verduras'),
(5, 'Yakitori Mix', 3, 9.0, '/assets/carta/Carnes/yakitori.webp', 1, 'Brochetas de pollo y puerro'),
(6, 'Tataki de Ternera', 1, 15.0, '/assets/carta/Carnes/beef_tataki.webp', 1, 'Ternera sellada con salsa cítrica'),
(7, 'Gyudon Bowl', 1, 13.0, '/assets/carta/Carnes/gyudon.webp', 1, 'Cuenco de arroz con finas láminas de ternera');

INSERT INTO pescado (id, nombre, cantidad, precio, imagen, id_carta, descripcion) VALUES
(1, 'Tataki de Atún', 1, 16.0, '/assets/carta/Pescados/tataki_atun.webp', 1, 'Atún sellado con costra de sésamo'),
(2, 'Salmón Teriyaki', 1, 15.5, '/assets/carta/Pescados/salmon_teriyaki.webp', 1, 'Lomo de salmón glaseado'),
(3, 'Bacalao Negro', 1, 22.0, '/assets/carta/Pescados/bacalao_negro.webp', 1, 'Bacalao marinado en miso dulce'),
(4, 'Sashimi Variado', 1, 25.0, '/assets/carta/Pescados/sashimi.webp', 1, 'Cortes de pescado crudo premium'),
(5, 'Hamachi Grill', 1, 14.5, '/assets/carta/Pescados/hamachi.webp', 1, 'Pescado amarillo a la brasa'),
(6, 'Unagi Kabayaki', 1, 19.0, '/assets/carta/Pescados/unagi_kaba.webp', 1, 'Anguila entera glaseada'),
(7, 'Caballa Salada', 1, 10.5, '/assets/carta/Pescados/caballa.webp', 1, 'Caballa asada al estilo tradicional');

INSERT INTO postre (id, nombre, cantidad, precio, imagen, id_carta, descripcion) VALUES
(1, 'Mochi Fresa', 2, 4.5, '/assets/carta/Postres/mochi_fresa.webp', 1, 'Pastel de arroz relleno de crema'),
(2, 'Dorayaki', 1, 3.8, '/assets/carta/Postres/dorayaki.webp', 1, 'Pancakes rellenos de judía roja'),
(3, 'Tarta Matcha', 1, 6.0, '/assets/carta/Postres/tarta_matcha.webp', 1, 'Tarta cremosa de queso y té verde'),
(4, 'Helado Sésamo', 1, 4.0, '/assets/carta/Postres/helado_sesamo.webp', 1, 'Helado artesanal de sésamo negro'),
(5, 'Taiyaki', 1, 4.2, '/assets/carta/Postres/taiyaki.webp', 1, 'Bizcocho en forma de pez con chocolate'),
(6, 'Gelatina Sake', 1, 5.0, '/assets/carta/Postres/gelatina_sake.webp', 1, 'Postre ligero con aroma de sake'),
(7, 'Fruta Matcha', 1, 3.5, '/assets/carta/Postres/fruta_matcha.webp', 1, 'Selección de frutas con sirope de té');

-- 4. RELACIONES PRODUCTO-INGREDIENTE
INSERT INTO sushi_ingrediente (id_sushi, id_ingrediente) VALUES
(1,1), (1,2), (1,4), (2,1), (2,3), (2,4), (3,1), (3,8), (3,9), (4,1), (4,10), (5,1), (5,9), (5,4), (6,4), (6,9), (7,1), (7,3), (7,15);

INSERT INTO ramen_ingrediente (id_ramen, id_ingrediente) VALUES
(1,5), (1,6), (1,12), (2,5), (2,6), (2,13), (3,5), (3,6), (3,11), (4,5), (4,14), (4,15), (5,5), (5,6), (6,5), (6,14), (7,5), (7,6), (7,14);

INSERT INTO entrante_ingrediente (id_entrante, id_ingrediente) VALUES
(1, 12), (1, 14), (2, 8), (3, 7), (4, 4), (5, 16), (5, 14), (6, 19), (6, 7), (7, 13), (7, 24);

INSERT INTO carne_ingrediente (id_carne, id_ingrediente) VALUES
(1, 18), (1, 15), (2, 19), (2, 7), (3, 12), (3, 17), (4, 18), (4, 14), (5, 19), (5, 7), (6, 18), (6, 15), (7, 18), (7, 1);

INSERT INTO pescado_ingrediente (id_pescado, id_ingrediente) VALUES
(1, 3), (1, 15), (2, 2), (2, 7), (3, 25), (3, 13), (4, 2), (4, 3), (5, 7), (6, 10), (6, 23), (7, 15);

INSERT INTO postre_ingrediente (id_postre, id_ingrediente) VALUES
(1, 1), (1, 21), (2, 11), (3, 20), (3, 11), (4, 15), (5, 22), (5, 11), (6, 11), (7, 20), (7, 9);

-- 5. CATEGORÍAS Y PEDIDOS
INSERT INTO categoria (nombre, slug) VALUES 
('tienda.categorias.todos', 'todos'),
('tienda.categorias.sushi', 'sushi'),
('tienda.categorias.ramen', 'ramen'),
('tienda.categorias.carne', 'carne'),
('tienda.categorias.pescado', 'pescado'),
('tienda.categorias.postres', 'postre'),
('tienda.categorias.bebidas', 'bebida'),
('tienda.categorias.entrantes', 'entrante');

-- PEDIDOS
INSERT INTO pedido (precio, id_usuario) VALUES
(31.50, 2),
(16.50, 2),
(91.50, 2);

-- LUEGO LAS LÍNEAS DE PEDIDO
INSERT INTO linea_pedido (cantidad, precio, nombre_plato, categoria_plato, ingredientes_quitados, ingredientesids, id_pedido) VALUES
(1, 10.50, 'Sushi Salmón', 'sushi', 'salmon', '[]', 1),
(1, 13.50, 'Ramen Tonkotsu', 'ramen', '', '[]', 1),
(1, 3.00, 'Ramune', 'bebida', '', '[]', 1);

INSERT INTO linea_pedido (cantidad, precio, nombre_plato, categoria_plato, ingredientes_quitados, ingredientesids, id_pedido) VALUES
(2, 5.50, 'Gyozas', 'entrante', '', '[]', 2),
(1, 1.50, 'Agua Mineral', 'bebida', '', '[]', 2);

INSERT INTO linea_pedido (cantidad, precio, nombre_plato, categoria_plato, ingredientes_quitados, ingredientesids, id_pedido) VALUES
(1, 45.00, 'Wagyu A5', 'carne', '', '[]', 3),
(1, 25.00, 'Sashimi Variado', 'pescado', '', '[]', 3);