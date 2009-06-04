/*
  msemicon example application
*/

#include <stdio.h>
#include "../WinTypes.h"
#include "../ftd2xx.h"

#define BUF_SIZE 0x100000 // 1 Megabyte

int main(int argc, char *argv[])
{

  /*
    definitions
  */

  DWORD EventDWord;
  DWORD RxBytes;
  DWORD TxBytes;
  DWORD data_out;		   
  DWORD w_data_len = 1;
  DWORD data_written;	
  int iCount;
  char Buf[0xFFFF]= {0};  /* initialized to zeros */
  char *s;
  ULONG bytesRead;
  ULONG bytesWritten;
  DWORD InTransferSize = 0xFFFF;
  
  #define READ_ONLY 10
  
  FT_HANDLE fthandleA;
  FT_HANDLE fthandleB;
  FT_STATUS status;
  
  UCHAR MaskA = 0xFF;
  UCHAR modeA = 1;
  UCHAR MaskB = 0xFF;
  UCHAR modeB = 1;

  /*
  open
  */
  
  status = FT_Open(0, &fthandleA);
  if(status != FT_OK) {
    printf("open A status not ok %d\n", status);
    return 0;
  } else {
    printf("open A status ok %d\n", status);
  }
  
  status = FT_Open(1, &fthandleB);
  if(status != FT_OK) {
    printf("open B status not ok %d\n", status);

    return 0;
  } else {
    printf("open B status ok %d\n", status);
  }

  /*
    set parameters
   */

  status = FT_SetUSBParameters(fthandleA, InTransferSize, 0);
  if (status == FT_OK) {
    printf("USB A buffer OK\n");
  } else {
    printf("USB A buffer not OK\n");
  }

  status = FT_SetUSBParameters(fthandleB, InTransferSize, 0);
  if (status == FT_OK) {
    printf("USB B buffer OK\n");
  } else {
    printf("USB B buffer not OK\n");
  }

  /*
    reset
  */

  status = FT_ResetDevice(fthandleA);
  if(status != FT_OK){
    printf("reset A status not ok %d\n", status);
  } else {
    printf("reset A status ok %d\n", status);
  }

  status = FT_ResetDevice(fthandleB);
  if(status != FT_OK) {
    printf("reset B status not ok %d\n", status);
  } else {
    printf("reset B status ok %d\n", status);
  }

  /*
   set timeouts
  */

  status = FT_SetTimeouts(fthandleA,500,500);
  if(status != FT_OK) {
    printf("timeout A status not ok %d\n", status);
  }

  status = FT_SetTimeouts(fthandleB,500,500);
  if(status != FT_OK) {
    printf("timeout B status not ok %d\n", status);
  }

  /*
    set baud rate
  */

  status = FT_SetBaudRate(fthandleA,12000000);
  if(status != FT_OK) {
    printf("baud A status not ok %d\n", status);
  }   

  // status = FT_SetBaudRate(fthandleB,12000000);
  // if(status != FT_OK) {
  //   printf("baud B status not ok %d\n", status);
  // }

  /* bit bash port A I/O */

  status = FT_SetBitMode(fthandleA, MaskA, modeA);
  if(status != FT_OK) {
    printf("mode A status not ok %d\n", status);
  }

  data_out = 0xAA;
  status = FT_Write(fthandleA, &data_out, w_data_len, &data_written);
  printf("DATA A is %x\n", data_out);
  
  getchar();
  
  data_out = 0x55;
  status = FT_Write(fthandleA, &data_out, w_data_len, &data_written);
  printf("DATA A is %x\n", data_out);

  /* Use MProg to set Port B to 245 FIFO mode in EEPROM first */	      

  do {
    UINT buf_cnt = 0;	
    FILE *f = fopen("test.bin","rb+");

    if (f == NULL) {
      printf("The file didn't open.\n");
      return 0;
    } else {
      printf("The file open.\n");
    }
    
    s = Buf;
    FT_GetStatus(fthandleB,&RxBytes,&TxBytes,&EventDWord);
    printf("TXByte =  %x  RXByte = %i\n ",TxBytes,RxBytes);
    FT_Read(fthandleB,s,RxBytes,&bytesWritten);
    getchar();
    FT_Write(fthandleB,s,1,&bytesWritten);

    do {
      printf("Buffer data =  %d\n",Buf[buf_cnt]);
      buf_cnt++;
    } while (buf_cnt < 100);

    iCount = fwrite(Buf,1,RxBytes,f);
    if (iCount < RxBytes) {
      printf("Read < 0xFFFF %d\n",RxBytes);
    } else {
      printf("Read = %d\n",iCount);
    }

    fclose(f); /* Must close file to read externaly */

    getchar();
  } while(1);
	
 status = FT_Close(fthandleA);
 status = FT_Close(fthandleB);

 return 0;


}
