-- 코드를 입력하세요
SELECT pt_name, pt_no, gend_cd, age, coalesce(tlno, 'NONE') tlno
from patient
where gend_cd = 'w' and age <= 12
order by 4 desc, 1
