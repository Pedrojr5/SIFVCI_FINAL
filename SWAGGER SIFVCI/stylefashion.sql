create database if not exists stylefashion;

use stylefashion;



select p.id, c.nomcat, p.color, p.marca, p.precio, p.talla from productos p join categorias c on p.idcat = c.idcat;
select * from users; 

describe productos;
describe categorias;













SELECT 
    u.username,
    r.role_name AS role_name,
    p.name AS permission_name
FROM
    users u
        INNER JOIN
    user_roles ur ON u.id = ur.user_id
        INNER JOIN
    roles r ON ur.role_id = r.id
        INNER JOIN
    role_permissions rp ON r.id = rp.role_id
        INNER JOIN
    permissions p ON rp.permission_id = p.id; 