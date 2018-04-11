function isHighDisk(diabetes,sex,smoking,age,bloodPressure,cholesterol){
	//参数：是否糖尿病(true,false)/性别(1男,2女)/吸烟(true,false)/年龄/血压/胆固醇
	if(cholesterol<99){
		if(diabetes){//糖尿病
			if(sex == 1){//男
				if(smoking){//吸烟
					if(age >= 70){
						if(bloodPressure < 140 && cholesterol < 5){
							return false;
						}else{
							return true;
						}
					}else if(age >= 60){
						if((bloodPressure < 160 && cholesterol<5)||(bloodPressure < 140 && cholesterol<7)){
							return false;
						}else{
							return true;
						}
					}else if(age >= 50){
						if((bloodPressure < 160 && cholesterol<7)||bloodPressure < 140){
							return false;
						}else{
							return true;
						}
					}else if(age >= 40){
						if((bloodPressure < 180 && cholesterol<5)||(bloodPressure < 160 && cholesterol<8)
								|| bloodPressure < 140){
							return false;
						}else{
							return true;
						}
					}
				}else{
					if(age >= 70){
						if(bloodPressure < 140 && cholesterol < 7){
							return false;
						}else{
							return true;
						}
					}else if(age >= 60){
						if((bloodPressure < 160 && cholesterol<7)||bloodPressure < 140){
							return false;
						}else{
							return true;
						}
					}else if(age >= 50){
						if((bloodPressure < 180 && cholesterol<7)||bloodPressure < 160){
							return false;
						}else{
							return true;
						}
					}else if(age >= 40){
						if((bloodPressure < 180 && cholesterol<7)||bloodPressure < 160){
							return false;
						}else{
							return true;
						}
					}
				}
			}else{//女
				if(smoking){//吸烟
					if(age >= 70){
						if(bloodPressure < 140 && cholesterol < 6){
							return false;
						}else{
							return true;
						}
					}else if(age >= 60){
						if((bloodPressure < 160 && cholesterol<6)||(bloodPressure < 140 && cholesterol<7)){
							return false;
						}else{
							return true;
						}
					}else if(age >= 50){
						if((bloodPressure < 180 && cholesterol<5)||(bloodPressure < 160 && cholesterol<7)||bloodPressure < 140){
							return false;
						}else{
							return true;
						}
					}else if(age >= 40){
						if((bloodPressure < 180 && cholesterol<5)||(bloodPressure < 160 && cholesterol<8)
								|| bloodPressure < 140){
							return false;
						}else{
							return true;
						}
					}
				}else{
					if(age >= 70){
						if((bloodPressure < 160 && cholesterol < 6)||bloodPressure < 140){
							return false;
						}else{
							return true;
						}
					}else if(age >= 60){
						if((bloodPressure < 160 && cholesterol<7)||bloodPressure < 140){
							return false;
						}else{
							return true;
						}
					}else if(age >= 50){
						if((bloodPressure < 180 && cholesterol<7)||(bloodPressure < 160 && cholesterol<8)
								||bloodPressure < 140){
							return false;
						}else{
							return true;
						}
					}else if(age >= 40){
						if((bloodPressure < 180 && cholesterol<7)||(bloodPressure < 160 && cholesterol<8)
								||bloodPressure < 140){
							return false;
						}else{
							return true;
						}
					}
				}
			}
			
		}else{//非糖尿病
			if(sex == 1){//男
				if(smoking){//吸烟
					if(age >= 70){
						if((bloodPressure < 160 && cholesterol < 5)||(bloodPressure < 140 && cholesterol < 8)){
							return false;
						}else{
							return true;
						}
					}else if(age >= 60){
						if((bloodPressure < 160 && cholesterol<7)||bloodPressure < 140){
							return false;
						}else{
							return true;
						}
					}else if(age >= 50){
						if((bloodPressure < 180 && cholesterol<6)||(bloodPressure < 160 && cholesterol<8)||bloodPressure < 140){
							return false;
						}else{
							return true;
						}
					}else if(age >= 40){
						if((bloodPressure < 180 && cholesterol<7)||(bloodPressure < 160 && cholesterol<8)||bloodPressure < 140){
							return false;
						}else{
							return true;
						}
					}
				}else{
					if(age >= 70){
						if((bloodPressure < 180 && cholesterol<5)||(bloodPressure < 160 && cholesterol<8)||bloodPressure < 140){
							return false;
						}else{
							return true;
						}
					}else if(age >= 60){
						if((bloodPressure < 180 && cholesterol<6)||bloodPressure < 160){
							return false;
						}else{
							return true;
						}
					}else if(age >= 50){
						if((bloodPressure < 180 && cholesterol<8)||bloodPressure < 160){
							return false;
						}else{
							return true;
						}
					}else if(age >= 40){
						if((bloodPressure < 180 && cholesterol<8)||bloodPressure < 160){
							return false;
						}else{
							return true;
						}
					}
				}
			}else{//女
				if(smoking){//吸烟
					if(age >= 70){
						if((bloodPressure < 180 && cholesterol<5)||(bloodPressure < 160 && cholesterol<8)||bloodPressure < 140){
							return false;
						}else{
							return true;
						}
					}else if(age >= 60){
						if((bloodPressure < 180 && cholesterol<6)||(bloodPressure < 160 && cholesterol<8)||bloodPressure < 140){
							return false;
						}else{
							return true;
						}
					}else if(age >= 50){
						if((bloodPressure < 180 && cholesterol<7)||(bloodPressure < 160 && cholesterol<8)||bloodPressure < 140){
							return false;
						}else{
							return true;
						}
					}else if(age >= 40){
						if((bloodPressure < 180 && cholesterol<7)||(bloodPressure < 160 && cholesterol<8)||bloodPressure < 140){
							return false;
						}else{
							return true;
						}
					}
				}else{
					if(age >= 70){
						if((bloodPressure < 180 && cholesterol<8)||bloodPressure < 160){
							return false;
						}else{
							return true;
						}
					}else if(age >= 60){
						if((bloodPressure < 180 && cholesterol<8)||bloodPressure < 160){
							return false;
						}else{
							return true;
						}
					}else if(age >= 50){
						if((bloodPressure < 200 && cholesterol<5)||(bloodPressure < 180 && cholesterol<8)||bloodPressure < 160){
							return false;
						}else{
							return true;
						}
					}else if(age >= 40){
						if((bloodPressure < 200 && cholesterol<5)||(bloodPressure < 180 && cholesterol<8)||bloodPressure < 160){
							return false;
						}else{
							return true;
						}
					}
				}
			}
		}
	}else{
		if(diabetes){//糖尿病
			if(sex == 1){//男
				if(smoking){//吸烟
					if(age >= 70){
						return true;
					}else if(age >= 60){
						if(bloodPressure < 140){
							return false;
						}else{
							return true;
						}
					}else if(age >= 50){
						if(bloodPressure < 160){
							return false;
						}else{
							return true;
						}
					}else if(age >= 40){
						if(bloodPressure < 180){
							return false;
						}else{
							return true;
						}
					}
				}else{
					if(age >= 70){
						if(bloodPressure < 140){
							return false;
						}else{
							return true;
						}
					}else if(age >= 60){
						if(bloodPressure < 160){
							return false;
						}else{
							return true;
						}
					}else if(age >= 50){
						if(bloodPressure < 180){
							return false;
						}else{
							return true;
						}
					}else if(age >= 40){
						if(bloodPressure < 180){
							return false;
						}else{
							return true;
						}
					}
				}
			}else{//女
				if(smoking){//吸烟
					if(age >= 70){
						if(bloodPressure < 140){
							return false;
						}else{
							return true;
						}
					}else if(age >= 60){
						if(bloodPressure < 160){
							return false;
						}else{
							return true;
						}
					}else if(age >= 50){
						if(bloodPressure < 180){
							return false;
						}else{
							return true;
						}
					}else if(age >= 40){
						if(bloodPressure < 180){
							return false;
						}else{
							return true;
						}
					}
				}else{
					if(age >= 70){
						if(bloodPressure < 160){
							return false;
						}else{
							return true;
						}
					}else if(age >= 60){
						if(bloodPressure < 180){
							return false;
						}else{
							return true;
						}
					}else if(age >= 50){
						if(bloodPressure < 180){
							return false;
						}else{
							return true;
						}
					}else if(age >= 40){
						if(bloodPressure < 180){
							return false;
						}else{
							return true;
						}
					}
				}
			}
		}else{//非糖尿病
			if(sex == 1){//男
				if(smoking){//吸烟
					if(age >= 70){
						if(bloodPressure < 140){
							return false;
						}else{
							return true;
						}
					}else if(age >= 60){
						if(bloodPressure < 160){
							return false;
						}else{
							return true;
						}
					}else if(age >= 50){
						if(bloodPressure < 180){
							return false;
						}else{
							return true;
						}
					}else if(age >= 40){
						if(bloodPressure < 180){
							return false;
						}else{
							return true;
						}
					}
				}else{
					if(age >= 70){
						if(bloodPressure < 160){
							return false;
						}else{
							return true;
						}
					}else if(age >= 60){
						if(bloodPressure < 180){
							return false;
						}else{
							return true;
						}
					}else if(age >= 50){
						if(bloodPressure < 180){
							return false;
						}else{
							return true;
						}
					}else if(age >= 40){
						if(bloodPressure < 180){
							return false;
						}else{
							return true;
						}
					}
				}
			}else{//女
				if(smoking){//吸烟
					if(age >= 70){
						if(bloodPressure < 160){
							return false;
						}else{
							return true;
						}
					}else if(age >= 60){
						if(bloodPressure < 180){
							return false;
						}else{
							return true;
						}
					}else if(age >= 50){
						if(bloodPressure < 180){
							return false;
						}else{
							return true;
						}
					}else if(age >= 40){
						if(bloodPressure < 180){
							return false;
						}else{
							return true;
						}
					}
				}else{
					if(age >= 70){
						if(bloodPressure < 180){
							return false;
						}else{
							return true;
						}
					}else if(age >= 60){
						if(bloodPressure < 180){
							return false;
						}else{
							return true;
						}
					}else if(age >= 50){
						if(bloodPressure < 180){
							return false;
						}else{
							return true;
						}
					}else if(age >= 40){
						if(bloodPressure < 180){
							return false;
						}else{
							return true;
						}
					}
				}
			}
		}
	}
}