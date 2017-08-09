package com.wang.io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * ��ʱû�б�Ҫ�洢�����������Ϣ����ֻ��Ҫ�洢һ������ĳ�Ա���ݣ���Ա���ݵ����ͼ��趼��Java�Ļ����������ͣ�
 * ���������󲻱�ʹ�õ���Object���롢�����ص������󣬿���ʹ��DataInputStream��DataOutputStream��д���������ݡ�
 * ������һ�����ӣ���DataInputStream�ĺô������ڴ��ļ���������ʱ��
 * ���÷��ĵ������ж϶����ַ���ʱ�����int����ʱ��ʱ��ֹͣ��
 * ʹ�ö�Ӧ��readUTF()��readInt()�����Ϳ�����ȷ�ض����������������ݡ���
 *
 */
public class DataInputStreamTest {
	
	static class Member{
		
		private String name;
		private int age;
		
		public Member(){
			
		}
		public Member(String name,int age){
			this.name = name;
			this.age = age;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		
		@Override
		public String toString() {
			return "Member[" + name +", " + age + "]";
		}
	}
	
	
	public static void main(String[] args) {
		
		Member[] members = {new Member("Justin",90),
							new Member("Momor",95),
							new Member("Bush",88)};
		try {
			DataOutputStream dataOutputStream = 
					new DataOutputStream(new FileOutputStream("file/io/temp.txt"));
			
			for(Member member:members){
				//д��UTF�ַ���
				dataOutputStream.writeUTF(member.getName());
				//д��int����
				dataOutputStream.writeInt(member.getAge());
			}
			
			//����������Ŀ�ĵ�   ��������������д���ļ�
			dataOutputStream.flush();
			
			//�ر���
			dataOutputStream.close();
			
			
			DataInputStream dataInputStream = 
					new DataInputStream(new FileInputStream("file/io/temp.txt"));
			
			
			//�������ݲ���ԭΪ����
			for(int i=0; i<members.length; i++){
				//����UTF�ַ���
				String name = dataInputStream.readUTF();
				//����int����
				int age = dataInputStream.readInt();
				
				members[i] = new Member(name,age);
			}
			
			//�ر���
			dataInputStream.close();
			
			
			//�����ԭ�������
			for(Member member:members){
				System.out.println(member);
			}
			
			
			
		} catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	
	
}
