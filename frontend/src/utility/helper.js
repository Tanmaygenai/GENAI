export const blobToBase64 = (blob) => {
    return new Promise((resolve, reject) => {
        const reader = new FileReader();
    
        reader.onload = () => {
          const base64String = reader.result.split(',')[1]; // Extract the Base64 portion of the data URL
          resolve(base64String);
        };
    
        reader.onerror = (error) => {
          reject(error);
        };
    
        reader.readAsDataURL(blob);
      });   
}