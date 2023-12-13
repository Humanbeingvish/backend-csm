package com.cognicx.AppointmentRemainder.constant;

public class CampaignQueryConstant {

	public static final String INSERT_CAMPAIGN_DET = "INSERT INTO CBMDB.campaign_det(campaign_id,name,description,status,max_adv_time,retry_delay,retry_count,concurrent_call,start_date,start_time,end_date,end_time,ftp_location,ftp_credentials,file_name,call_before) "
			+ "VALUES (:campaignId,:name,:desc,:status,:maxAdvTime,:retryDelay,:retryCount,:concurrentCall,:startDate,:startTime,:endDate,:endTime,:ftpLocation,:ftpCredentials,:fileName,:callBefore)";

	public static final String INSERT_CAMPAIGN_WEEK_DET = "insert into CBMDB.campaign_week_det (campaign_id,day,status,start_time,end_time) values(:campaignId,:day,:status,:startTime,:endTime)";

	public static final String GET_CAMPAIGN_ID = "select max(SUBSTRING(campaign_id, 3, 100)) from CBMDB.campaign_det";

	public static final String GET_CAMPAIGN_DET = "SELECT campaign_id,name,description,status,max_adv_time,retry_delay,retry_count,concurrent_call,start_date,start_time,campaign_det.end_date,end_time,ftp_location,ftp_credentials,file_name,call_before FROM CBMDB.campaign_det";

	public static final String GET_CAMPAIGN_WEEK_DET = "SELECT campaign_week_id,campaign_id,day,status,start_time,end_time from CBMDB.campaign_week_det";

	public static final String UPDATE_CAMPAIGN_DET = "UPDATE CBMDB.campaign_det SET name = :name,status = :status,max_adv_time = :maxAdvTime,retry_delay = :retryDelay,retry_count = :retryCount,concurrent_call = :concurrentCall,start_date = :startDate,start_time = :startTime,end_date = :endDate,end_time = :endTime,ftp_location = :ftpLocation,ftp_credentials = :ftpCredentials,rec_updt_dt = getdate(),call_before=:callBefore,file_name=:fileName WHERE campaign_id = :campaignId";

	public static final String UPDATE_CAMPAIGN_WEEK_DET = "UPDATE CBMDB.campaign_week_det SET day=:day, status=:status, start_time=:startTime, end_time=:endTime where campaign_week_id=:campaignWeekId";

	public static final String UPDATE_CALL_DET = "UPDATE CBMDB.contact_det SET caller_response=:callerResponse, call_status=:callStatus, call_duration=:callDuration, call_retry_count=:retryCount,rec_upt_date=getdate() where contact_id=:contactId";

	public static final String INSERT_CONTACT_DET = "insert into CBMDB.contact_det (campaign_id,campaign_name,doctor_name,patient_name,contact_number,appointment_date,language,call_status,upload_history_id) values(:campaignId,:campaignName,:doctorName,:patientName,:contactNo,:appDate,:language,:callStatus,:historyId)";

	public static final String GET_CONTACT_DET = "select campaign_id,campaign_name,doctor_name,patient_name,contact_number,appointment_date,language,contact_id,call_retry_count,rec_upt_date,call_status from CBMDB.contact_det where call_status !='ANSWERED' order by appointment_date asc";
	
	

	public static final String VALIDATE_CAMPAIGN_NAME = "select count(1) from [CBMDB].[campaign_det] where name=:name";

	public static final String GET_SUMMARY_REPORT_DET = "select campaign_name,format(appointment_date,'dd-MMM-yyyy'),count(contact_id) as totalContact,SUM(CASE WHEN call_status!='New' THEN 1 ELSE 0 END) as contactCalled,"
			+ "  SUM(CASE WHEN call_status in ('ANSWERED','BUSY','NOANSWER','NOT ANSWERED')  THEN 1 ELSE 0 END) as connectedCall,"
			+ "  SUM(CASE WHEN call_status='NOT ANSWERED'  THEN 1 ELSE 0 END) as answered,"
			+ "  SUM(CASE WHEN call_status='BUSY'  THEN 1 ELSE 0 END) as busy,"
			+ "  SUM(CASE WHEN caller_response='1'  THEN 1 ELSE 0 END) as confirmed,"
			+ "  SUM(CASE WHEN caller_response='2'  THEN 1 ELSE 0 END) as cancelled,"
			+ "  SUM(CASE WHEN caller_response='3'  THEN 1 ELSE 0 END) as rescheduled,"
			+ "  SUM(CASE WHEN caller_response='0'  THEN 1 ELSE 0 END) as noresponse,"
			+ "  SUM(CASE WHEN call_status not in ('ANSWERED','BUSY','NOANSWER','NOT ANSWERED')  THEN 1 ELSE 0 END) as others"
			+ "  from CBMDB.contact_det"
			+ "  where cast(appointment_date as date) between :startDate and :endDate and campaign_id =:name"
			+ "  group by campaign_name,format(appointment_date,'dd-MMM-yyyy')";

	public static final String GET_CONTACT_DET_REPORT = "SELECT contact_id,campaign_id,campaign_name,doctor_name,patient_name,contact_number,format(appointment_date,'dd-MMM-yyyy hh:mm:ss tt'),caller_response ,call_status ,call_duration ,call_retry_count FROM CBMDB.contact_det where";

	public static final String GET_CALL_RETRY_DET = "select contact_id,call_status,format(rec_add_dt,'dd-MMM-yyyy hh:mm:ss tt') from CBMDB.call_retry_det where contact_id in (:contactIdList) order by contact_id asc";

	public static final String INSERT_CALL_RETRY_DET = "insert into CBMDB.call_retry_det (contact_id,call_status,retry_count) values (:contactId,:callStatus,:retryCount)";

	public static final String GET_UPLOAD_HISTORY_DETIALS = "select upload_history_id,campaign_id,campaign_name,uploaded_on,file_name from CBMDB.upload_history_det where status=1 and cast(uploaded_on as date) between :startDate and :endDate";

	public static final String DELETE_CONTACT_BY_HISTORY = "delete from CBMDB.contact_det where upload_history_id=:historyId";
	
	public static final String GET_CUSTOMER_DATA = " select customer_data_id,last_four_digits,customer_mobile_number,total_due,minimum_payment,due_date,status from CBMDB.customer_data where status = 'Active' ";
	
	
	public static final String GET_SUMMARY_REPORT_DET_CBI = "select campaign_name,format(appointment_date,'dd-MMM-yyyy'),count(contact_id) as totalContact,"
			+ "  SUM(CASE WHEN call_status!='New' THEN 1 ELSE 0 END) as contactCalled,"
			+ "  SUM(CASE WHEN call_status in ('ANSWERED','BUSY','NOANSWER','NOT ANSWERED')  THEN 1 ELSE 0 END) as connectedCall,"
			+ "  SUM(CASE WHEN call_status='NOT ANSWERED'  THEN 1 ELSE 0 END) as answered,"
			+ "  SUM(CASE WHEN call_status='BUSY'  THEN 1 ELSE 0 END) as busy,"
			+ "  SUM(CASE WHEN caller_response='1'  THEN 1 ELSE 0 END) as confirmed,"
			+ "  SUM(CASE WHEN caller_response='2'  THEN 1 ELSE 0 END) as cancelled,"
			+ "  SUM(CASE WHEN caller_response='3'  THEN 1 ELSE 0 END) as rescheduled,"
			+ "  SUM(CASE WHEN caller_response='0'  THEN 1 ELSE 0 END) as noresponse,"
			+ "  SUM(CASE WHEN call_status not in ('ANSWERED','BUSY','NOANSWER','NOT ANSWERED')  THEN 1 ELSE 0 END) as others"
			+ "  from CBMDB.contact_det"
			+ "  where cast(appointment_date as date) between :startDate and :endDate and campaign_id =:name"
			+ "  group by campaign_name,format(appointment_date,'dd-MMM-yyyy')";
	
	
	public static final String GET_CONTACT_DET_REPORT_CBI = "SELECT contact_id,campaign_id,campaign_name,doctor_name,patient_name,contact_number,format(appointment_date,'dd-MMM-yyyy hh:mm:ss tt') as appointment_date,caller_response ,call_status ,call_duration ,call_retry_count, "
			+ " agent_id,language,customer_name,cif,customer_mobile_number,call_back_number,format(called_on,'dd-MMM-yyyy hh:mm:ss tt') as called_on,format(call_back_registered_date,'dd-MMM-yyyy hh:mm:ss tt') as call_back_registered_date "
			+ " FROM CBMDB.contact_det where ";
}
