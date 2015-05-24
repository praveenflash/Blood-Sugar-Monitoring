// Program to test ADC 0808. The output pins are connected to LED's. external clock is used for driving the ADC 0808.
#include<reg51.h>
sbit ale=P3^0;  //address latch enable
sbit oe=P3^1;  //output enable
sbit sc=P3^3;  //start conversion
sbit eoc=P3^2;  //end of conversion
sbit te=P3^4;
sfr input_port=0x90;
sfr output_port=0xA0;

void delay(unsigned int count)  // Function to provide time delay in msec.
{
int i,j;
for(i=0;i<count;i++)
  for(j=0;j<1275;j++);
}

void main()
{
unsigned int temp1=0,temp2=0,i;
te=1;
eoc=1;
input_port=0xFF;
ale=0;
oe=0;
sc=0;
while(1)
{
  delay(2);
  ale=1;
  delay(2);
  sc=1;
  delay(1);
  ale=0;
  delay(1);
  sc=0;
  while(eoc==1);
  while(eoc==0);
  oe=1;
  temp2=input_port;
  if(temp1>temp2)
     break;
  else
     temp1=temp2;
  delay(2);
  oe=0;
}
output_port=temp1;
te=0;
delay(2);
te=1;
for(i=0;i<4;i++)
   temp1>>=1;

output_port=temp1;
te=0;
delay(2);
te=1;
}