getNeedProcess
===
SELECT
	t.sensor_str,
	substring(t.sensor_str, 9, 2) type,
	substring(t.sensor_str, 5, 4) sensor_sn,
	substring(t.sensor_str, 11, 2) xflag,
	substring(t.sensor_str, 11, 16) xyvalue,
CONVERT (
	(
		CASE
		WHEN substring(t.sensor_str, 11, 2) = '00' THEN
			CONCAT(
				substring(t.sensor_str, 13, 2),
				'.',
				substring(t.sensor_str, 15, 4)
			)
		ELSE
			CONCAT(
				'-',
				substring(t.sensor_str, 13, 2),
				'.',
				substring(t.sensor_str, 15, 4)
			)
		END
	),
		DECIMAL (10, 4)
	)  xval,
	t.x,
	CONVERT (
		(
			CASE
			WHEN substring(t.sensor_str, 19, 2) = '00' THEN
				CONCAT(
					substring(t.sensor_str, 21, 2),
					'.',
					substring(t.sensor_str, 23, 4)
				)
			ELSE
				CONCAT(
					'-',
					substring(t.sensor_str, 21, 2),
					'.',
					substring(t.sensor_str, 23, 4)
				)
			END
		),
		DECIMAL (10, 4)
	) yval,
	t.y
FROM
	device_data_debug t
WHERE
	x > 0
LIMIT 10