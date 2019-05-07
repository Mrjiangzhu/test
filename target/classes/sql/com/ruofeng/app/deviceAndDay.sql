/*获取要处理的传感器设备编号列表*/
getSensorSnByDay
===
SELECT
	t.sensor_sn
FROM
	device_data_debug t
WHERE
	DATE_FORMAT(t.upload_time, '%Y-%m-%d') = #day#
GROUP BY
	t.sensor_sn



listBySnAndDay
===
SELECT * FROM device_data_debug t
WHERE 1=1
    and DATE_FORMAT(t.upload_time, '%Y-%m-%d') = #day#
    and t.sensor_sn= #sensorSn#
order by t.upload_time DESC
limit 1

