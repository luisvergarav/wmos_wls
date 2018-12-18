<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas" xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" exclude-result-prefixes=" oracle-xsl-mapper xsi xsd xsl oraxsl">
   <oracle-xsl-mapper:schema>
      <!--SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY.-->
      <oracle-xsl-mapper:mapSources>
         <oracle-xsl-mapper:source type="XSD">
            <oracle-xsl-mapper:schema location="../XSD/CORP_CORP_Appointment_Req.xsd"/>
            <oracle-xsl-mapper:rootElement name="AppointmentReq" namespace=""/>
         </oracle-xsl-mapper:source>
      </oracle-xsl-mapper:mapSources>
      <oracle-xsl-mapper:mapTargets>
         <oracle-xsl-mapper:target type="XSD">
            <oracle-xsl-mapper:schema location="../XSD/Appointment.xsd"/>
            <oracle-xsl-mapper:rootElement name="tXML" namespace=""/>
         </oracle-xsl-mapper:target>
      </oracle-xsl-mapper:mapTargets>
      <!--GENERATED BY ORACLE XSL MAPPER 12.2.1.1.0(XSLT Build 160608.1900.0023) AT [MON NOV 05 16:30:45 CLST 2018].-->
   </oracle-xsl-mapper:schema>
   <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
   <xsl:template match="/">
      <tXML>
         <Header>
            <Source>
               <xsl:value-of select="/AppointmentReq/Header/Source"/>
            </Source>
            <Action_Type>
               <xsl:value-of select="/AppointmentReq/Header/Action_Type"/>
            </Action_Type>
            <xsl:if test="/AppointmentReq/Header/Sequence_Number">
               <Sequence_Number>
                  <xsl:value-of select="/AppointmentReq/Header/Sequence_Number"/>
               </Sequence_Number>
            </xsl:if>
            <xsl:if test="/AppointmentReq/Header/Batch_ID">
               <Batch_ID>
                  <xsl:value-of select="/AppointmentReq/Header/Batch_ID"/>
               </Batch_ID>
            </xsl:if>
            <xsl:if test="/AppointmentReq/Header/Reference_ID">
               <Reference_ID>
                  <xsl:value-of select="/AppointmentReq/Header/Reference_ID"/>
               </Reference_ID>
            </xsl:if>
            <xsl:if test="/AppointmentReq/Header/User_ID">
               <User_ID>
                  <xsl:value-of select="/AppointmentReq/Header/User_ID"/>
               </User_ID>
            </xsl:if>
            <xsl:if test="/AppointmentReq/Header/Password">
               <Password>
                  <xsl:value-of select="/AppointmentReq/Header/Password"/>
               </Password>
            </xsl:if>
            <Message_Type>
               <xsl:value-of select="/AppointmentReq/Header/Message_Type"/>
            </Message_Type>
            <Company_ID>
               <xsl:value-of select="/AppointmentReq/Header/Company_ID"/>
            </Company_ID>
            <xsl:if test="/AppointmentReq/Header/Msg_Locale">
               <Msg_Locale>
                  <xsl:value-of select="/AppointmentReq/Header/Msg_Locale"/>
               </Msg_Locale>
            </xsl:if>
            <xsl:if test="/AppointmentReq/Header/Msg_Time_Zone">
               <Msg_Time_Zone>
                  <xsl:value-of select="/AppointmentReq/Header/Msg_Time_Zone"/>
               </Msg_Time_Zone>
            </xsl:if>
            <xsl:if test="/AppointmentReq/Header/Version">
               <Version>
                  <xsl:value-of select="/AppointmentReq/Header/Version"/>
               </Version>
            </xsl:if>
            <xsl:if test="/AppointmentReq/Header/Send_Message">
               <Send_Message>
                  <xsl:value-of select="/AppointmentReq/Header/Send_Message"/>
               </Send_Message>
            </xsl:if>
            <xsl:if test="/AppointmentReq/Header/Carrier_Company">
               <Carrier_Company>
                  <xsl:value-of select="/AppointmentReq/Header/Carrier_Company"/>
               </Carrier_Company>
            </xsl:if>
            <xsl:if test="/AppointmentReq/Header/Sequence">
               <Sequence>
                  <xsl:value-of select="/AppointmentReq/Header/Sequence"/>
               </Sequence>
            </xsl:if>
         </Header>
         <Message>
            <xsl:for-each select="/AppointmentReq/Message/Appointment">
               <Appointment>
                  <xsl:if test="Appointment_ID">
                     <Appointment_ID>
                        <xsl:value-of select="Appointment_ID"/>
                     </Appointment_ID>
                  </xsl:if>
                  <Appointment_Type>
                     <xsl:value-of select="Appointment_Type"/>
                  </Appointment_Type>
                  <Facility_Alias_ID>
                     <xsl:value-of select="Facility_Alias_ID"/>
                  </Facility_Alias_ID>
                  <xsl:if test="Appointment_Status">
                     <Appointment_Status>
                        <xsl:value-of select="Appointment_Status"/>
                     </Appointment_Status>
                  </xsl:if>
                  <xsl:if test="Seal_Number">
                     <Seal_Number>
                        <xsl:value-of select="Seal_Number"/>
                     </Seal_Number>
                  </xsl:if>
                  <xsl:if test="Carrier_Code">
                     <Carrier_Code>
                        <xsl:value-of select="Carrier_Code"/>
                     </Carrier_Code>
                  </xsl:if>
                  <xsl:if test="Scheduled_Dttm">
                     <Scheduled_Dttm>
                        <xsl:value-of select="Scheduled_Dttm"/>
                     </Scheduled_Dttm>
                  </xsl:if>
                  <xsl:if test="Actual_CheckIn_Dttm">
                     <Actual_CheckIn_Dttm>
                        <xsl:value-of select="Actual_CheckIn_Dttm"/>
                     </Actual_CheckIn_Dttm>
                  </xsl:if>
                  <xsl:if test="Check_Out_Dttm">
                     <Check_Out_Dttm>
                        <xsl:value-of select="Check_Out_Dttm"/>
                     </Check_Out_Dttm>
                  </xsl:if>
                  <xsl:if test="Load_Unload_Start_Dttm">
                     <Load_Unload_Start_Dttm>
                        <xsl:value-of select="Load_Unload_Start_Dttm"/>
                     </Load_Unload_Start_Dttm>
                  </xsl:if>
                  <xsl:if test="Load_Unload_End_Dttm">
                     <Load_Unload_End_Dttm>
                        <xsl:value-of select="Load_Unload_End_Dttm"/>
                     </Load_Unload_End_Dttm>
                  </xsl:if>
                  <xsl:if test="Appointment_priority">
                     <Appointment_priority>
                        <xsl:value-of select="Appointment_priority"/>
                     </Appointment_priority>
                  </xsl:if>
                  <xsl:if test="Driver_Duration_In_Min">
                     <Driver_Duration_In_Min>
                        <xsl:value-of select="Driver_Duration_In_Min"/>
                     </Driver_Duration_In_Min>
                  </xsl:if>
                  <xsl:if test="Control_number">
                     <Control_number>
                        <xsl:value-of select="Control_number"/>
                     </Control_number>
                  </xsl:if>
                  <xsl:if test="Requestor_name">
                     <Requestor_name>
                        <xsl:value-of select="Requestor_name"/>
                     </Requestor_name>
                  </xsl:if>
                  <xsl:if test="Requestor_type">
                     <Requestor_type>
                        <xsl:value-of select="Requestor_type"/>
                     </Requestor_type>
                  </xsl:if>
                  <xsl:if test="Reserved_appt_id">
                     <Reserved_appt_id>
                        <xsl:value-of select="Reserved_appt_id"/>
                     </Reserved_appt_id>
                  </xsl:if>
                  <xsl:if test="Tp_Company_Id">
                     <Tp_Company_Id>
                        <xsl:value-of select="Tp_Company_Id"/>
                     </Tp_Company_Id>
                  </xsl:if>
                  <xsl:if test="Appt_Reason_Id">
                     <Appt_Reason_Id>
                        <xsl:value-of select="Appt_Reason_Id"/>
                     </Appt_Reason_Id>
                  </xsl:if>
                  <xsl:if test="Appt_Reason_Code">
                     <Appt_Reason_Code>
                        <xsl:value-of select="Appt_Reason_Code"/>
                     </Appt_Reason_Code>
                  </xsl:if>
                  <xsl:if test="Trailer_Duration_In_Min">
                     <Trailer_Duration_In_Min>
                        <xsl:value-of select="Trailer_Duration_In_Min"/>
                     </Trailer_Duration_In_Min>
                  </xsl:if>
                  <xsl:if test="Driver_ID">
                     <Driver_ID>
                        <xsl:value-of select="Driver_ID"/>
                     </Driver_ID>
                  </xsl:if>
                  <xsl:if test="Cancel_Reason_code">
                     <Cancel_Reason_code>
                        <xsl:value-of select="Cancel_Reason_code"/>
                     </Cancel_Reason_code>
                  </xsl:if>
                  <xsl:if test="Communication_Mode">
                     <Communication_Mode>
                        <xsl:value-of select="Communication_Mode"/>
                     </Communication_Mode>
                  </xsl:if>
                  <xsl:if test="Appointment_Comments">
                     <Appointment_Comments>
                        <xsl:value-of select="Appointment_Comments"/>
                     </Appointment_Comments>
                  </xsl:if>
                  <xsl:if test="Business_partner">
                     <Business_partner>
                        <xsl:value-of select="Business_partner"/>
                     </Business_partner>
                  </xsl:if>
                  <xsl:if test="Cancel_Indicator">
                     <Cancel_Indicator>
                        <xsl:value-of select="Cancel_Indicator"/>
                     </Cancel_Indicator>
                  </xsl:if>
                  <xsl:if test="Load_position">
                     <Load_position>
                        <xsl:value-of select="Load_position"/>
                     </Load_position>
                  </xsl:if>
                  <xsl:if test="Business_Unit">
                     <Business_Unit>
                        <xsl:value-of select="Business_Unit"/>
                     </Business_Unit>
                  </xsl:if>
                  <Requested_Time>
                     <xsl:value-of select="Requested_Time"/>
                  </Requested_Time>
                  <xsl:if test="template_Id">
                     <template_Id>
                        <xsl:value-of select="template_Id"/>
                     </template_Id>
                  </xsl:if>
                  <xsl:if test="Cancelled_Tc_Appt_Id">
                     <Cancelled_Tc_Appt_Id>
                        <xsl:value-of select="Cancelled_Tc_Appt_Id"/>
                     </Cancelled_Tc_Appt_Id>
                  </xsl:if>
                  <xsl:if test="Appointment_Source">
                     <Appointment_Source>
                        <xsl:value-of select="Appointment_Source"/>
                     </Appointment_Source>
                  </xsl:if>
                  <xsl:if test="Load_Configuration">
                     <Load_Configuration>
                        <xsl:value-of select="Load_Configuration"/>
                     </Load_Configuration>
                  </xsl:if>
                  <xsl:if test="Equipment_ID">
                     <Equipment_ID>
                        <xsl:value-of select="Equipment_ID"/>
                     </Equipment_ID>
                  </xsl:if>
                  <xsl:if test="Equipment_Code">
                     <Equipment_Code>
                        <xsl:value-of select="Equipment_Code"/>
                     </Equipment_Code>
                  </xsl:if>
                  <xsl:if test="Tractor_ID">
                     <Tractor_ID>
                        <xsl:value-of select="Tractor_ID"/>
                     </Tractor_ID>
                  </xsl:if>
                  <xsl:if test="Tractor_Type">
                     <Tractor_Type>
                        <xsl:value-of select="Tractor_Type"/>
                     </Tractor_Type>
                  </xsl:if>
                  <xsl:if test="Equipment">
                     <Equipment>
                        <xsl:value-of select="Equipment"/>
                     </Equipment>
                  </xsl:if>
                  <xsl:if test="Dock_Id">
                     <Dock_Id>
                        <xsl:value-of select="Dock_Id"/>
                     </Dock_Id>
                  </xsl:if>
                  <xsl:if test="Door_Id">
                     <Door_Id>
                        <xsl:value-of select="Door_Id"/>
                     </Door_Id>
                  </xsl:if>
                  <xsl:for-each select="ApptObject">
                     <ApptObject>
                        <xsl:if test="Object_Type">
                           <Object_Type>
                              <xsl:value-of select="Object_Type"/>
                           </Object_Type>
                        </xsl:if>
                        <xsl:if test="Object_ID">
                           <Object_ID>
                              <xsl:value-of select="Object_ID"/>
                           </Object_ID>
                        </xsl:if>
                        <xsl:if test="Stop_Seq">
                           <Stop_Seq>
                              <xsl:value-of select="Stop_Seq"/>
                           </Stop_Seq>
                        </xsl:if>
                        <xsl:for-each select="Purchase_Order">
                           <Purchase_Order>
                              <xsl:if test="Booked_Size">
                                 <Booked_Size>
                                    <xsl:value-of select="Booked_Size"/>
                                 </Booked_Size>
                              </xsl:if>
                              <xsl:if test="Size_UOM">
                                 <Size_UOM>
                                    <xsl:value-of select="Size_UOM"/>
                                 </Size_UOM>
                              </xsl:if>
                              <xsl:if test="TC_Order_Id">
                                 <TC_Order_Id>
                                    <xsl:value-of select="TC_Order_Id"/>
                                 </TC_Order_Id>
                              </xsl:if>
                           </Purchase_Order>
                        </xsl:for-each>
                     </ApptObject>
                  </xsl:for-each>
                  <xsl:for-each select="Appointment_Slots">
                     <Appointment_Slots>
                        <xsl:if test="Slot_Id">
                           <Slot_Id>
                              <xsl:value-of select="Slot_Id"/>
                           </Slot_Id>
                        </xsl:if>
                        <xsl:if test="Slot_Start_Dttm">
                           <Slot_Start_Dttm>
                              <xsl:value-of select="Slot_Start_Dttm"/>
                           </Slot_Start_Dttm>
                        </xsl:if>
                        <xsl:if test="Slot_Duration">
                           <Slot_Duration>
                              <xsl:value-of select="Slot_Duration"/>
                           </Slot_Duration>
                        </xsl:if>
                        <xsl:if test="Capacity_Used">
                           <Capacity_Used>
                              <xsl:value-of select="Capacity_Used"/>
                           </Capacity_Used>
                        </xsl:if>
                     </Appointment_Slots>
                  </xsl:for-each>
                  <xsl:for-each select="Custom_Attribute">
                     <Custom_Attribute>
                        <xsl:if test="Attribute_Name">
                           <Attribute_Name>
                              <xsl:value-of select="Attribute_Name"/>
                           </Attribute_Name>
                        </xsl:if>
                        <xsl:if test="Attribute_Value">
                           <Attribute_Value>
                              <xsl:value-of select="Attribute_Value"/>
                           </Attribute_Value>
                        </xsl:if>
                     </Custom_Attribute>
                  </xsl:for-each>
               </Appointment>
            </xsl:for-each>
         </Message>
      </tXML>
   </xsl:template>
</xsl:stylesheet>