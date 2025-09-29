-- 코드를 입력하세요
SELECT dr_name, dr_id, mcdp_cd, date_format(hire_ymd, '%Y-%m-%d') hire_ymd
from doctor
where mcdp_cd = "cs" or mcdp_cd = "gs"
order by hire_ymd desc, 1;