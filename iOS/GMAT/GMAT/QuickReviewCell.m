//
//  QuickReviewCell.m
//  GMAT
//
//  Created by Trung Đức on 4/9/16.
//  Copyright © 2016 Trung Đức. All rights reserved.
//

#import "QuickReviewCell.h"
#import "Constant.h"

@implementation QuickReviewCell

- (void)awakeFromNib {
    [super awakeFromNib];
    // Initialization code
    
    self.viewCir.textStyle               = MCPercentageDoughnutViewTextStyleUserDefined;
    self.viewCir.linePercentage          = 0.1;
    self.viewCir.animationDuration       = 1;
    self.viewCir.showTextLabel           = YES;
    self.viewCir.animatesBegining        = YES;
    self.viewCir.textLabel.font          = [UIFont systemFontOfSize:5];
    self.viewCir.textLabel.textColor     = [kAppColor colorWithAlphaComponent:0.5];
    self.viewCir.fillColor = [UIColor redColor];
    
    
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

@end
